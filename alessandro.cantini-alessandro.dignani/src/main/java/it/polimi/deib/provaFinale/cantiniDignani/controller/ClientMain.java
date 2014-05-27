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

		aspettaEvento(InizioPartita.class);
	}

	public static void setEventoCorrente(Evento eventoCorrente) {
		ClientMain.eventoCorrente = eventoCorrente;
	}

	private static void aspettaEvento(Class<?> classe) {
		while (eventoCorrente.getClass() != classe) {
			Utilita.aspetta(100);
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

	public static void setDatiPartita(DatiPartita datiPartita) {
		ClientMain.datiPartita = datiPartita;
	}

}
