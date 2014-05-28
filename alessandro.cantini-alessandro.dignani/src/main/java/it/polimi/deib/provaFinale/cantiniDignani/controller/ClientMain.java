package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneRMI;
import it.polimi.deib.provaFinale.cantiniDignani.rete.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.view.Cli;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientMain {
	private static String nome;
	private static InterfacciaUtente ui;
	private static ConnessioneClient connessione;
	private static DatiPartita datiPartita;
	private static LinkedBlockingQueue<Evento> codaEventi = new LinkedBlockingQueue<Evento>();
	private static Mossa[] mosseDisponibili;

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();

		connessione.inizializza();
				
		registraGiocatore();
		
	
	}

	/**
	 * Aspetta e restituisce un evento inviato dal server
	 * 
	 * @return l'evento ricevuto dal server
	 */
	private static Evento aspettaEvento() {
		Evento evento = null;
		try {
			evento = codaEventi.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return evento;
	}

	/**
	 * Aspetta e restituisce un evento di un determinato tipo, controlla che
	 * l'evento ricevuto sia del tipo passato come parametro, in caso negativo
	 * fa terminare il programma
	 * 
	 * @param classe
	 *            il tipo di evento che ci si aspetta
	 * @return l'evento ricevuto dal server
	 */
	private static Evento aspettaEvento(Class<?> classe) {
		Evento e = aspettaEvento();
		if (e.getClass() != classe) {
			throw new RuntimeException("L'evento è diverso da quello aspettato");
		}
		return e;
	}

	private static void gestisciProprioTurno() {
		Mossa mossaScelta;
		mosseDisponibili = connessione.chiediMosseDisponibili();
		mossaScelta = ui.chiediMossa(mosseDisponibili);
		connessione.inviaMossa(mossaScelta);
	}

	private static boolean isProprioTurno() {
		return datiPartita.getGiocatoreDiTurno().equals(nome) ? true : false;
	}

	/*
	 * private static void gestisciEvento() { eventoCorrente =
	 * connessione.chiediEvento(); eventoCorrente.aggiornaDati();
	 * eventoCorrente.visualizza(); }
	 */

	private static void registraGiocatore() {
		while (true) {
			try {
				nome = ui.chiediNome();
				connessione.registraGiocatore(nome);
				break;
			} catch (NomeGiaPresenteException e) {
				ui.nomeGiaPresente();
			}catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static ConnessioneClient chiediTipoConnessione() {
		// TODO Auto-generated method stub
		return new ConnessioneRMI();
	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		// TODO Auto-generated method stub
		return new Cli();
	}

	public static Queue<Evento> getCodaEventi() {
		return codaEventi;
	}

	public static String getNome() {
		return nome;
	}

	public static InterfacciaUtente getUI() {
		return ui;
	}

	public static ConnessioneClient getConnessione() {
		return connessione;
	}

	public static DatiPartita getDatiPartita() {
		return datiPartita;
	}

	public static void setDatiPartita(DatiPartita datiPartita) {
		ClientMain.datiPartita = datiPartita;
	}

}
