package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ServerRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ServerSocketImpl;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.CostantiCli;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

public class ServerMain {
	public final static PrintStream LOGGER = CostantiCli.DEFAULT_OUTPUT;

	private static final List<GestorePartita> gestoriPartita = new Vector<GestorePartita>();
	private static final List<String> giocatoriInAttesa = new Vector<String>();
	private static final GestoreCoda<Integer> gestoreEventi = new GestoreCoda<Integer>();
	private static final TimerPartita timerPartita = new TimerPartita(CostantiRete.MILLISECONDI_TIMER_PARTITA);

	private static InterfacciaConnessioneServer connessione;

	public static void main(String[] args) {
		timerPartita.start();

		impostaTipoConnessione();
		connessione.inizia();
	}

	public static synchronized boolean aggiungiGiocatore(String nome) {
		if (nomeGiaRegistrato(nome)) {
			return false;
		}

		giocatoriInAttesa.add(nome);

		if (giocatoriInAttesa.size() == Costanti.NUM_MAX_GIOCATORI) {
			iniziaPartita();
		}

		if (giocatoriInAttesa.size() >= Costanti.NUM_MIN_GIOCATORI) {
			timerPartita.inizia();
		}

		return true;
	}

	public synchronized static void iniziaPartita() {

		if (giocatoriInAttesa.size() < 2 || giocatoriInAttesa.size() > Costanti.NUM_MAX_GIOCATORI) {
			throw new SheeplandException("La partita non puo' iniziare con " + giocatoriInAttesa.size() + "giocatori");
		}

		timerPartita.ferma();
		Partita partita = new Partita(giocatoriInAttesa);
		GestorePartita gestore = new GestorePartita(partita, connessione, gestoreEventi);
		gestoriPartita.add(gestore);
		gestore.start();
		giocatoriInAttesa.clear();
	}

	/**
	 * Controlla se un nome e' gia' stato registrato sul server
	 * 
	 * @param nome
	 *            il nome da controllare
	 * @return true se e' gia' stato registrato, false se il nome e' disponibile
	 */
	private static boolean nomeGiaRegistrato(String nome) {
		for (GestorePartita gest : gestoriPartita) {
			for (Giocatore gioc : gest.getPartita().getGiocatori()) {
				if (nome.equals(gioc.getNome())) {
					return true;
				}
			}
		}
		for (String s : giocatoriInAttesa) {
			if (nome.equals(s)) {
				return true;
			}
		}
		return false;
	}

	private static void impostaTipoConnessione() {
		// TODO chiedo all'utente che tipo di server vuole e creo l'oggetto

		// TODO da rimuovere, test

		if (CostantiRete.RMI) {
			connessione = new ServerRmi(gestoreEventi);
		} else {
			connessione = new ServerSocketImpl(gestoreEventi);
		}
	}

	/**
	 * Restituisce la partita giocata da un giocatore
	 * 
	 * @param giocatore
	 *            il nome del giocatore
	 * @return la partita giocata dal giocatore
	 */
	public static Partita getPartita(String giocatore) {
		for (GestorePartita gest : gestoriPartita) {
			for (Giocatore g : gest.getPartita().getGiocatori()) {
				if (giocatore.equals(g.getNome())) {
					return gest.getPartita();
				}
			}
		}

		throw new IllegalArgumentException("Il giocatore non è presente");

	}

}
