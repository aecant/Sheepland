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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ServerMain {
	public final static PrintStream LOGGER = CostantiCli.DEFAULT_OUTPUT;

	private static final List<GestorePartita> gestoriPartita = new Vector<GestorePartita>();
	private static final List<Utente> utentiInAttesa = new Vector<Utente>();
	private static final TimerPartita timerPartita = new TimerPartita(CostantiRete.MILLISECONDI_TIMER_PARTITA);

	private static InterfacciaConnessioneServer connessione;

	public static void main(String[] args) {
		timerPartita.start();

		impostaTipoConnessione();
		connessione.inizia();
	}

	public static synchronized boolean aggiungiUtente(String nome, String password) {
		Utente utente = new Utente(nome, password);
		if (utenteGiaRegistrato(utente)) {
			return false;
		}
		
		utentiInAttesa.add(utente);
		LOGGER.println(utente + "registrato");

		if (utentiInAttesa.size() == Costanti.NUM_MAX_GIOCATORI) {
			iniziaPartita();
		}

		if (utentiInAttesa.size() >= Costanti.NUM_MIN_GIOCATORI) {
			timerPartita.inizia();
		}

		return true;
	}

	public synchronized static void iniziaPartita() {

		if (utentiInAttesa.size() < 2 || utentiInAttesa.size() > Costanti.NUM_MAX_GIOCATORI) {
			throw new SheeplandException("La partita non puo' iniziare con " + utentiInAttesa.size() + "giocatori");
		}

		timerPartita.ferma();
		List<String> nomi = new ArrayList<String>();
		for(Utente u : utentiInAttesa) {
			nomi.add(u.getNome());
		}
		
		List<Utente> utentiPartita = new ArrayList<Utente>();
		utentiPartita.addAll(utentiInAttesa);
		GestorePartita gestore = new GestorePartita(utentiPartita, connessione);
		gestoriPartita.add(gestore);
		gestore.start();
		utentiInAttesa.clear();
	}

	/**
	 * Controlla se un nome e' gia' stato registrato sul server
	 * 
	 * @param nome
	 *            il nome da controllare
	 * @return true se e' gia' stato registrato, false se il nome e' disponibile
	 */
	private static boolean utenteGiaRegistrato(Utente utente) {
		for (GestorePartita gest : gestoriPartita) {
			for (Utente u : gest.getUtenti()) {
				if (utente.equals(u)) {
					return true;
				}
			}
		}
		for (Utente u : utentiInAttesa) {
			if (utente.equals(u)) {
				return true;
			}
		}
		return false;
	}

	private static void impostaTipoConnessione() {
		// TODO chiedo all'utente che tipo di server vuole e creo l'oggetto

		// TODO da rimuovere, test

		if (CostantiTest.RMI) {
			connessione = new ServerRmi();
		} else {
			connessione = new ServerSocketImpl();
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
	
	public static Utente getUtente(String nome) {
		for(GestorePartita gest : gestoriPartita) {
			for(Utente ut : gest.getUtenti()) {
				if(ut.getNome().equals(nome)) {
					return ut;
				}
			}
		}
		for(Utente ut : utentiInAttesa) {
			if(ut.getNome().equals(nome)) {
				return ut;
			}
		}
		
		throw new IllegalArgumentException(nome + " non presente ");
	}

}
