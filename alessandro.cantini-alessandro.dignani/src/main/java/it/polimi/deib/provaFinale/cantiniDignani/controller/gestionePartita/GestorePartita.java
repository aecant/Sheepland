package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import java.util.ArrayList;
import java.util.List;

public class GestorePartita extends Thread {

	private final List<Utente> utenti;
	private final Partita partita;
	private final List<String> tuttiGiocatori;

	private final PreparazionePartita preparazionePartita;
	private final FaseIniziale faseIniziale;
	private final FasePrincipale fasePrincipale;
	private final FaseFinale faseFinale;

	public final boolean dueGiocatori;
	public final int denaroIniziale;

	public GestorePartita(List<Utente> utenti) {
		this.utenti = utenti;

		tuttiGiocatori = new ArrayList<String>();
		for (Utente u : utenti) {
			tuttiGiocatori.add(u.getNome());
		}

		partita = new Partita(tuttiGiocatori);

		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = Costanti.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = Costanti.DENARO_INIZIALE;
		}

		preparazionePartita = new PreparazionePartita(this);
		faseIniziale = new FaseIniziale(this);
		fasePrincipale = new FasePrincipale(this);
		faseFinale = new FaseFinale(this);

	}

	public void run() {

		preparazionePartita.esegui();

		faseIniziale.esegui();

		fasePrincipale.esegui();

		faseFinale.esegui();
	}

	protected int lanciaDado(MotivoLancioDado motivo) {
		int lancio = Sorte.lanciaDado();
		inviaEventoATutti(new LancioDado(lancio, motivo));
		return lancio;
	}

	protected int aspettaMossa(Giocatore giocatore) {
		return getUtente(giocatore).getCodaMosse().aspetta();
	}

	public void inviaEventoATutti(Evento evento) {
		for (Giocatore g : partita.getGiocatori()) {
			inviaEvento(evento, g);
		}
	}

	public void inviaEvento(Evento evento, Giocatore g) {
		Utente utente = getUtente(g);
		if (utente.getConnessione() != null) {
			utente.inviaEvento(evento);
		}
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

	public Partita getPartita() {
		return partita;
	}

	protected List<String> getTuttiGiocatori() {
		return tuttiGiocatori;
	}

	public PreparazionePartita getPreparazionePartita() {
		return preparazionePartita;
	}

	protected FaseIniziale getFaseIniziale() {
		return faseIniziale;
	}

	protected FasePrincipale getFasePrincipale() {
		return fasePrincipale;
	}

	protected FaseFinale getFaseFinale() {
		return faseFinale;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	protected boolean giocatoreOffline(Giocatore giocatore) {
		return getUtente(giocatore).getConnessione() == null;
	}

	private Utente getUtente(Giocatore giocatore) {
		for (Utente u : utenti) {
			if (u.getNome().equals(giocatore.getNome())) {
				return u;
			}
		}
		throw new IllegalArgumentException(giocatore + " non presente in " + utenti);
	}

}
