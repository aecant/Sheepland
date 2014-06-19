package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreDisconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneServerRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ConnessioneServerSocket;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.CostantiCli;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ServerSheepland {
	public final static PrintStream LOGGER = CostantiCli.DEFAULT_OUTPUT;

	private final List<GestorePartita> gestoriPartita = new Vector<GestorePartita>();
	private final List<Utente> utentiInAttesa = new Vector<Utente>();
	private final List<Utente> utenti = new Vector<Utente>();
	private final TimerPartita timerPartita = new TimerPartita(CostantiRete.MILLISECONDI_TIMER_PARTITA, this);

	private final ConnessioneServerRmi connessioneRmi = new ConnessioneServerRmi(this);
	private final ConnessioneServerSocket connessioneSocket = new ConnessioneServerSocket(this);

	public void inizia() {
		timerPartita.start();

		connessioneRmi.start();
		connessioneSocket.start();

	}

	public synchronized boolean aggiungiUtente(String nome, String password, InterfacciaConnessioneServer connessione) {
		Utente utente = new Utente(nome, password, connessione);
		if (utenteGiaRegistrato(utente)) {
			return false;
		}

		utentiInAttesa.add(utente);
		utenti.add(utente);
		LOGGER.println("Registrato " + utente);

		if (utentiInAttesa.size() == Costanti.NUM_MAX_GIOCATORI) {
			iniziaPartita();
		}

		if (utentiInAttesa.size() >= Costanti.NUM_MIN_GIOCATORI) {
			timerPartita.inizia();
		}

		return true;
	}

	public synchronized void iniziaPartita() {

		if (utentiInAttesa.size() < 2 || utentiInAttesa.size() > Costanti.NUM_MAX_GIOCATORI) {
			throw new SheeplandException("La partita non puo' iniziare con " + utentiInAttesa.size() + "giocatori");
		}

		timerPartita.ferma();
		List<String> nomi = new ArrayList<String>();
		for (Utente u : utentiInAttesa) {
			nomi.add(u.getNome());
		}

		List<Utente> utentiPartita = new ArrayList<Utente>();
		utentiPartita.addAll(utentiInAttesa);
		GestorePartita gestore = new GestorePartita(utentiPartita);
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
	private boolean utenteGiaRegistrato(Utente utente) {
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

	/**
	 * Restituisce la partita giocata da un giocatore
	 * 
	 * @param giocatore
	 *            il nome del giocatore
	 * @return la partita giocata dal giocatore
	 */
	public Partita getPartita(String giocatore) {
		for (GestorePartita gest : gestoriPartita) {
			for (Giocatore g : gest.getPartita().getGiocatori()) {
				if (giocatore.equals(g.getNome())) {
					return gest.getPartita();
				}
			}
		}

		throw new IllegalArgumentException("Il giocatore non è presente");

	}

	public Utente getUtente(String nome) {
		for (GestorePartita gest : gestoriPartita) {
			for (Utente ut : gest.getUtenti()) {
				if (ut.getNome().equals(nome)) {
					return ut;
				}
			}
		}
		for (Utente ut : utentiInAttesa) {
			if (ut.getNome().equals(nome)) {
				return ut;
			}
		}

		throw new IllegalArgumentException(nome + " non presente ");
	}
	
	public void gestisciDisconnessione(Utente utente) {
		utente.getConnessione().gestisciDisconnessione(utente);
		utente.setConnessione(null);
		utente.getCodaMosse().aggiungi(CostantiRete.MOSSA_DISCONNESSIONE);
		
		GestorePartita gestore = getGestorePartita(utente);
		try {
			synchronized(gestore) {
				gestore.wait();
			}
			for(Utente utentePartita : gestore.getUtenti()) {
				utentePartita.inviaEvento(new GiocatoreDisconnesso(utente.getNome()));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.println(utente + "disconnesso");
		
	}
	
	private GestorePartita getGestorePartita(Utente utente) {
		for (GestorePartita gestore : gestoriPartita) {
			if (gestore.getUtenti().contains(utente)) {
				return gestore;
			}
		}
		throw new IllegalArgumentException(utente + " non trovato");
	}
	

}
