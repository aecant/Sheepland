package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneRMI;
import it.polimi.deib.provaFinale.cantiniDignani.rete.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.view.Cli;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

public class ClientMain {
	private static String nome;
	private static InterfacciaUtente ui;
	private static ConnessioneClient connessione;
	private static DatiPartita datiPartita;
	private static GestoreEventi gestoreEventi = new GestoreEventi();

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();

		connessione.inizializza();

		registraGiocatore();

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
		return new ConnessioneRMI();
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

	public static GestoreEventi getGestoreEventi() {
		return gestoreEventi;
	}

	public static void setDatiPartita(DatiPartita datiPartita) {
		ClientMain.datiPartita = datiPartita;
	}

}
