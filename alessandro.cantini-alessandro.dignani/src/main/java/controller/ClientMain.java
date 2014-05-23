package controller;

import rete.*;
import view.*;
import controller.eventi.Evento;
import controller.eventi.Mossa;

public class ClientMain {
	private static InterfacciaUtente ui;
	private static Connessione connessione;
	private static DatiPartita datiPartita;

	public static InterfacciaUtente getUI() {
		return ui;
	}

	public static Connessione getConnessione() {
		return connessione;
	}

	public static DatiPartita getDatiPartita() {
		return datiPartita;
	}

	public static void setDatiPartita(DatiPartita datiPartita) {
		ClientMain.datiPartita = datiPartita;
	}

	private static Mossa[] mosseDisponibili;
	private static Evento eventoCorrente;
	private static String nome;

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();

		registraGiocatore();

		do {
			iniziaPartita();
			eseguiPartita();
		} while (!nuovaPartita());

	}

	private static boolean nuovaPartita() {
		// TODO Auto-generated method stub
		return false;
	}

	private static void eseguiPartita() {
		while (true) {
			gestisciEvento();

			if (isProprioTurno()) {
				gestisciProprioTurno();
			}
		}
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

	private static void gestisciEvento() {
		eventoCorrente = connessione.chiediEvento();
		eventoCorrente.aggiornaDati();
		eventoCorrente.visualizza();
	}

	private static void iniziaPartita() {
		datiPartita = ui.scaricaDatiPartita();
	}

	private static void registraGiocatore() {

		// while(connessione.registraNome(utente.chiediNome()));
	}

	private static Connessione chiediTipoConnessione() {
		// TODO Auto-generated method stub
		return null;
	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		// TODO Auto-generated method stub
		return null;
	}

}
