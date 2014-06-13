package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorePartita extends Thread {

	private final Partita partita;
	private final GestoreCoda<Evento> gestoreEventi;
	private List<String> tuttiGiocatori;
	private final InterfacciaServer connessione;
	
	private final PreparazionePartita preparazionePartita;
	private final FaseIniziale faseIniziale;
	private final FasePrincipale fasePrincipale;
	private final FaseFinale faseFinale;

	public final boolean dueGiocatori;
	public final int denaroIniziale;

	public GestorePartita(Partita partita, InterfacciaServer connessione, GestoreCoda<Evento> gestoreEventi) {
		this.partita = partita;
		this.connessione = connessione;
		this.gestoreEventi = gestoreEventi;
		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = Costanti.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = Costanti.DENARO_INIZIALE;
		}
		tuttiGiocatori = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			tuttiGiocatori.add(g.getNome());
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

	protected Evento aspettaEvento(Class<? extends Evento> classe) {
		return gestoreEventi.aspetta(classe);
	}

	protected void inviaEventoATutti(Evento e) {
		getConnessione().inviaEvento(e, tuttiGiocatori);
	}

	protected void inviaEvento(Evento e, Giocatore g) {
		getConnessione().inviaEvento(e, Collections.singletonList(g.getNome()));
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

	public Partita getPartita() {
		return partita;
	}

	protected InterfacciaServer getConnessione() {
		return connessione;
	}

	protected List<String> getTuttiGiocatori() {
		return tuttiGiocatori;
	}

	public PreparazionePartita getPreparazionePartita() {
		return preparazionePartita;
	}

	protected FaseFinale getFaseFinale() {
		return faseFinale;
	}

}
