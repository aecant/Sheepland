package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreDisconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneServerRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ConnessioneServerSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

public class ServerSheepland {
	private final static Logger logger = Logger.getLogger(ServerSheepland.class.getName());

	private final List<GestorePartita> gestoriPartita = new Vector<GestorePartita>();
	private final List<Utente> utentiInAttesa = new Vector<Utente>();
	private final List<Utente> utentiOnline = new Vector<Utente>();
	private final List<Utente> utentiDisconnessi = new Vector<Utente>();
	
	private final TimerPartita timerPartita = new TimerPartita(CostantiRete.MILLISECONDI_TIMER_PARTITA, this);

	private final ConnessioneServerRmi connessioneRmi = new ConnessioneServerRmi(this);
	private final ConnessioneServerSocket connessioneSocket = new ConnessioneServerSocket(this);

	public void inizia() {
		timerPartita.start();

		connessioneRmi.start();
		logger.info("Server RMI pronto");
		connessioneSocket.start();
		logger.info("Server socket pronto");

	}

	public synchronized void aggiungiUtente(String nome, String password, InterfacciaConnessioneServer connessione) throws NomeGiaPresenteException, PasswordSbagliataException {
		Utente utente = new Utente(nome, password, connessione);
		if (utentiOnline.contains(utente)) {
			throw new NomeGiaPresenteException();
		}
		
		for(Utente u : utentiDisconnessi) {
			if(u.getNome().equals(utente.getNome())) {
				if(u.getPassword().equals(utente.getPassword())) {
					riconnettiUtente(utente);
				} else {
					throw new PasswordSbagliataException();
				}
			}
		}
		
		utentiInAttesa.add(utente);
		utentiOnline.add(utente);
		logger.info("Registrato " + utente);

		if (utentiInAttesa.size() == Costanti.NUM_MAX_GIOCATORI) {
			iniziaPartita();
		}

		if (utentiInAttesa.size() >= Costanti.NUM_MIN_GIOCATORI) {
			timerPartita.inizia();
		}

	}

	private void riconnettiUtente(Utente utente) {
		// TODO Auto-generated method stub
		
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
		utentiInAttesa.remove(utente);
		utentiOnline.remove(utente);
		utentiDisconnessi.add(utente);
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
		logger.info(utente + "disconnesso");
		
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
