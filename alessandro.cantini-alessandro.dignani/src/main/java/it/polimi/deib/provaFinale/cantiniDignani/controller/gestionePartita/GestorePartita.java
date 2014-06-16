package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorePartita extends Thread {

	private final Partita partita;
	private final GestoreCoda<Integer> gestoreEventi;
	private List<String> tuttiGiocatori;
	private final InterfacciaConnessioneServer connessione;
	
	private final PreparazionePartita preparazionePartita;
	private final FaseIniziale faseIniziale;
	private final FasePrincipale fasePrincipale;
	private final FaseFinale faseFinale;

	public final boolean dueGiocatori;
	public final int denaroIniziale;

	public GestorePartita(Partita partita, InterfacciaConnessioneServer connessione, GestoreCoda<Integer> gestoreEventi) {
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

	protected int aspettaMossa() {
		return gestoreEventi.aspetta();
	}

	protected void inviaEventoATutti(Evento evento) {
		connessione.inviaEvento(evento, tuttiGiocatori);
	}

	protected void inviaEvento(Evento e, Giocatore g) {
		connessione.inviaEvento(e, Collections.singletonList(g.getNome()));
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

	public Partita getPartita() {
		return partita;
	}

	protected InterfacciaConnessioneServer getConnessione() {
		return connessione;
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

}
