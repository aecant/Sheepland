package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneClientRmi;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.Cli;

public class ClientMain {
	private static String nome;
	private static InterfacciaUtente ui;
	private static ConnessioneClient connessione;
	private static DatiPartita datiPartita;
	private static GestoreCoda<Evento> gestoreEventi = new GestoreCoda<Evento>();

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();

		connessione.inizializza();

		registraGiocatore();
		
		effettuaPartita();
	}

	private static void effettuaPartita() {
		while(true) {
			Evento eventoCorrente = gestoreEventi.aspetta();
			gestisciEvento(eventoCorrente);
		}
	}

	private static void gestisciEvento(Evento evento) {
		evento.aggiornaDati();
		evento.visualizza();
	}

	private static void registraGiocatore() {
		while (true) {
			try {
				nome = ui.chiediNome();
				connessione.registraGiocatore(nome);
				break;
			} catch (NomeGiaPresenteException e) {
				ui.nomeGiaPresente();
			}
		}
	}

	private static ConnessioneClient chiediTipoConnessione() {
		// TODO test da rimuovere
		return new ConnessioneClientRmi();
	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		// TODO test da rimuovere
		return new Cli();
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

	public static GestoreCoda<Evento> getGestoreEventi() {
		return gestoreEventi;
	}

	public static void aggiornaDatiPartita() {
		datiPartita = connessione.scaricaDatiPartita();
	}

}
