package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreDisconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreRiconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneServerRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ConnessioneServerSocket;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSheepland {
	private final static Logger LOGGER = Logger.getLogger(ServerSheepland.class.getName());

	private final List<GestorePartita> gestoriPartita = new Vector<GestorePartita>();
	private final List<Utente> utentiInAttesa = new Vector<Utente>();
	private final List<Utente> utentiOnline = new Vector<Utente>();
	private final List<Utente> utentiDisconnessi = new Vector<Utente>();

	private final TimerPartita timerPartita = new TimerPartita(CostantiController.MILLISECONDI_TIMER_PARTITA, this);

	private final ConnessioneServerRmi connessioneRmi = new ConnessioneServerRmi(this);
	private final ConnessioneServerSocket connessioneSocket = new ConnessioneServerSocket(this);

	public void inizia() {
		connessioneRmi.start();
		connessioneSocket.start();
		
		timerPartita.start();
	}

	/**
	 * Inizia una partita con gli utenti in attesa
	 */
	public synchronized void iniziaPartita() {

		if (utentiInAttesa.size() < 2 || utentiInAttesa.size() > CostantiModel.NUM_MAX_GIOCATORI) {
			throw new SheeplandException("La partita non puo' iniziare con " + utentiInAttesa.size() + "giocatori");
		}

		timerPartita.ferma();
		List<String> nomi = new ArrayList<String>();
		for (Utente u : utentiInAttesa) {
			nomi.add(u.getNome());
		}

		List<Utente> utentiPartita = new Vector<Utente>();
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

		throw new ElementoNonPresenteException(giocatore + " non è presente");

	}

	/**
	 * Restituisce l'utente con un certo nome
	 * 
	 * @param nome
	 *            il nome dell'utente
	 * @return l'utente che ha il nome passato come parametro
	 */
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

		throw new ElementoNonPresenteException(nome + " non presente ");
	}

	/**
	 * Gestisce la disconnessione di un utente
	 * 
	 * @param utente
	 *            l'utente di cui gestire la disconnessione
	 */
	public void gestisciDisconnessione(Utente utente) {

		utentiInAttesa.remove(utente);
		utentiOnline.remove(utente);

		if (utente == null) {
			return;
		}

		utentiDisconnessi.add(utente);
		if (utente.getConnessione() != null) {
			utente.getConnessione().gestisciDisconnessione(utente);
			utente.setConnessione(null);
		}
		utente.getCodaMosse().aggiungi(CostantiController.MOSSA_DISCONNESSIONE);

		try {
			GestorePartita gestore = getGestorePartita(utente);

			for (Utente utentePartita : gestore.getUtenti()) {
				utentePartita.inviaEvento(new GiocatoreDisconnesso(utente.getNome()));
			}

			gestore.sospendiPartita();

			LOGGER.warning("Disconnesso " + utente + " nella partita " + gestore.getPartita());

		} catch (ElementoNonPresenteException e) {
			LOGGER.log(Level.WARNING, "Disconnesso " + utente, e);
		}

	}

	public synchronized void aggiungiUtente(String nome, String password, InterfacciaConnessioneServer connessione) throws NomeGiaPresenteException, PasswordSbagliataException {
		Utente utente = new Utente(nome, password, connessione);
		if (utentiOnline.contains(utente)) {
			throw new NomeGiaPresenteException();
		}

		utentiOnline.add(utente);

		for (Utente u : utentiDisconnessi) {
			if (u.getNome().equals(utente.getNome())) {
				if (u.getPassword().equals(utente.getPassword())) {
					riconnettiUtente(utente);
					return;
				} else {
					throw new PasswordSbagliataException();
				}
			}
		}

		utentiInAttesa.add(utente);
		LOGGER.info("Registrato " + utente);

		if (utentiInAttesa.size() == CostantiModel.NUM_MAX_GIOCATORI) {
			iniziaPartita();
		}

		if (utentiInAttesa.size() >= CostantiModel.NUM_MIN_GIOCATORI) {
			timerPartita.inizia();
		}

	}

	private void riconnettiUtente(Utente utente) {
		utentiDisconnessi.remove(utente);
		utente.getCodaMosse().svuota();
			
		GestorePartita gestore;
		try {
			gestore = getGestorePartita(utente);
		} catch (ElementoNonPresenteException e) {
			LOGGER.log(Level.FINE, "elemento non presente", e);
			return;
		}

		gestore.inviaEventoATutti(new GiocatoreRiconnesso(utente.getNome()));
		
		for (Utente u : Utilita.copia(gestore.getUtenti())) {
			if (utente.getNome().equals(u.getNome())) {
				gestore.getUtenti().remove(u);
			}
		}
		gestore.getUtenti().add(utente);

		synchronized (gestore) {
			gestore.notify();
		}
		
		new AvvertimentoRiconnessione(gestore, utente).start();

		LOGGER.info("riconnesso " + utente);
	}

	private GestorePartita getGestorePartita(Utente utente) {
		for (GestorePartita gestore : gestoriPartita) {
			if (gestore.getUtenti().contains(utente)) {
				return gestore;
			}
		}
		throw new ElementoNonPresenteException(utente + " non trovato");
	}

}
