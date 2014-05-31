package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ServerRMI;
import it.polimi.deib.provaFinale.cantiniDignani.view.Input;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
	private static Vector<Partita> partite = new Vector<Partita>();
	private static Vector<String> giocatoriInAttesa = new Vector<String>();
	private static InterfacciaServer connessione;
	private static ExecutorService esecutorePartite = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		impostaTipoConnessione();
		connessione.inizializza();
	}

	// TODO impostare timer per iniziare partita
	public synchronized static boolean aggiungiGiocatore(String nome) {
		if (nomeGiaRegistrato(nome))
			return false;

		giocatoriInAttesa.add(nome);

		if (giocatoriInAttesa.size() == Costanti.NUM_MAX_GIOCATORI) {
			iniziaPartita(new Partita(giocatoriInAttesa));
		}

		return true;
	}

	protected synchronized static void iniziaPartita(Partita partita) {
		partite.add(partita);
		esecutorePartite.submit(new GestorePartita(partita, connessione));
		giocatoriInAttesa.clear();
		System.out.println("Partita iniziata.");
	}

	/**
	 * Controlla se un nome e' gia' stato registrato sul server
	 * 
	 * @param nome
	 *            il nome da controllare
	 * @return true se e' gia' stato registrato, false se il nome e' disponibile
	 */
	private static boolean nomeGiaRegistrato(String nome) {
		for (Partita p : partite) {
			for (Giocatore g : p.getGiocatori()) {
				if (nome.equals(g.getNome())) {
					return true;
				}
			}
		}
		for (String s : giocatoriInAttesa) {
			if (nome.equals(s))
				return true;
		}
		return false;
	}

	private static void impostaTipoConnessione() {
		// TODO chiedo all'utente che tipo di server vuole e creo l'oggetto
	
		// TODO da rimuovere, test
		connessione = new ServerRMI();
	}

	/**
	 * Restituisce la partita giocata da un giocatore
	 * 
	 * @param giocatore
	 *            il nome del giocatore
	 * @return la partita giocata dal giocatore
	 */
	public static Partita getPartita(String giocatore) {
		for (Partita p : partite) {
			for (Giocatore g : p.getGiocatori()) {
				if (giocatore.equals(g.getNome())) {
					return p;
				}
			}
		}

		throw new IllegalArgumentException("Il giocatore non è presente");

	}

	public static InterfacciaServer getConnessione() {
		return connessione;
	}

}
