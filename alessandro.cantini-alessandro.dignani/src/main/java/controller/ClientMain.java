package controller;

import java.rmi.RemoteException;

import rete.ConnessioneClient;
import rete.ConnessioneRMI;
import rete.DatiPartita;
import rete.NomeGiaPresenteException;
import view.InterfacciaUtente;
import controller.eventi.Evento;
import controller.eventi.Mossa;

public class ClientMain {
	private static String nome;
	private static InterfacciaUtente ui;
	private static ConnessioneClient connessione;
	private static DatiPartita datiPartita;

	private static Mossa[] mosseDisponibili;

	private static Evento eventoCorrente;

	public static void main(String[] args) {
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();
		
		connessione.inizializza();
		
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
		datiPartita = connessione.scaricaDatiPartita();
	}

	private static void registraGiocatore() {
		while (true) {
			try {
				nome = ui.chiediNome();
				connessione.registraGiocatore(nome);
				break;
			} catch (NomeGiaPresenteException e) {
				ui.nomeGiaPresente();
			} catch (RemoteException e) {
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
		return null;
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
