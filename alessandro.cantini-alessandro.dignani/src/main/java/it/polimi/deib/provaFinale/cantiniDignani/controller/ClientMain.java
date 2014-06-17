package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneClientRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ConnessioneClientSocket;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.Cli;
import it.polimi.deib.provaFinale.cantiniDignani.view.gui.Gui;

public class ClientMain {
	private static String nome;
	private static InterfacciaUtente ui;
	private static InterfacciaConnessioneClient connessione;
	private static DatiPartita datiPartita;
	private static GestoreCoda<Evento> gestoreEventi = new GestoreCoda<Evento>();

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();

		connessione.inizia();

		chiediNomeERegistraGiocatore();

		effettuaPartita();
	}

	private static void effettuaPartita() {
		while (true) {
			Evento eventoCorrente = gestoreEventi.aspetta();
			gestisciEvento(eventoCorrente);
		}
	}

	private static void gestisciEvento(Evento evento) {
		evento.aggiornaDati();
		evento.visualizza();
	}

	private static void chiediNomeERegistraGiocatore() {
		boolean registrato = false;
		while (!registrato) {
			try {
				nome = ui.chiediNome();
				connessione.registraGiocatore(nome);
				registrato = true;
			} catch (NomeGiaPresenteException e) {
				ui.nomeGiaPresente();
			}
		}
	}

	private static InterfacciaConnessioneClient chiediTipoConnessione() {
		// TODO test da rimuovere
		if (CostantiTest.RMI) {
			return new ConnessioneClientRmi();
		} else {
			return new ConnessioneClientSocket();
		}
	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		// TODO test da rimuovere
		if (CostantiTest.CLI) {
			return new Cli();
		} else {
			return new Gui();
		}
	}

	public static String getNome() {
		return nome;
	}

	public static InterfacciaUtente getUI() {
		return ui;
	}

	public static InterfacciaConnessioneClient getConnessione() {
		return connessione;
	}

	public static DatiPartita getDatiPartita() {
		return datiPartita;
	}

	public static GestoreCoda<Evento> getGestoreEventi() {
		return gestoreEventi;
	}

	public static void setDatiPartita(DatiPartita datiPartita) {
		ClientMain.datiPartita = datiPartita;
	}

}
