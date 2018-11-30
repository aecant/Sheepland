package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.FinePartita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.rmi.ConnessioneClientRmi;
import it.polimi.deib.provaFinale.cantiniDignani.rete.socket.ConnessioneClientSocket;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.Cli;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.CostantiCli;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.InputCli;
import it.polimi.deib.provaFinale.cantiniDignani.view.gui.Gui;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.validator.routines.InetAddressValidator;

public class MainClient {

	private static final Logger LOGGER = Logger.getLogger(MainClient.class.getName());

	private static final InputCli INPUT = new InputCli(CostantiCli.DEFAULT_INPUT);
	private static final PrintStream OUTPUT = CostantiCli.DEFAULT_OUTPUT;

	private static String nome;
	private static String indirizzoServer;
	private static InterfacciaUtente ui;
	private static InterfacciaConnessioneClient connessione;
	private static DatiPartita datiPartita;
	private static GestoreCoda<Evento> codaEventi = new GestoreCoda<Evento>();

	// costruttore privato per nascondere quello pubblico
	private MainClient() {
	}
	
	/** 
	 * Il main del client
	 * @param args null
	 */
	public static void main(String[] args) {
		indirizzoServer = chiediIndirizzoServer();
		connessione = chiediTipoConnessione();
		ui = chiediTipoInterfaccia();
		connessione.inizia();
		chiediNomeERegistraGiocatore();
		effettuaPartita();
	}

	private static void effettuaPartita() {
		Evento eventoCorrente = null;
		while (!(eventoCorrente instanceof FinePartita)) {
			eventoCorrente = codaEventi.aspetta();
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
				Coppia<String, String> nomeEPassword = ui.chiediNomeEPassword();
				connessione.registraGiocatore(nomeEPassword);
				registrato = true;
				nome = nomeEPassword.getPrimo();
			} catch (NomeGiaPresenteException e) {
				LOGGER.log(Level.FINE, "nome gia' presente", e);
				ui.nomeGiaPresente();
			} catch (PasswordSbagliataException e) {
				LOGGER.log(Level.FINE, "password sbagliata", e);
				ui.passwordSbagliata();
			}
		}
	}

	private static String chiediIndirizzoServer() {
		String indirizzo = "";
		while (!InetAddressValidator.getInstance().isValid(indirizzo) && !indirizzo.equals("localhost")) {
			OUTPUT.println("Inserisci l'indirizzo IP del server");
			indirizzo = INPUT.leggiStringa();
		}
		return indirizzo;
	}

	private static InterfacciaConnessioneClient chiediTipoConnessione() {
		OUTPUT.println("1) Socket");
		OUTPUT.println("2) RMI");

		int scelta = INPUT.leggiIntero(1, 2);

		if (scelta == 1) {
			return new ConnessioneClientRmi(indirizzoServer, codaEventi);
		} else {
			return new ConnessioneClientSocket(indirizzoServer, codaEventi);
		}

	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		OUTPUT.println("1) CLI");
		OUTPUT.println("2) GUI");

		int scelta = INPUT.leggiIntero(1, 2);

		if (scelta == 1) {
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
		return codaEventi;
	}

	public static void setDatiPartita(DatiPartita datiPartita) {
		MainClient.datiPartita = datiPartita;
	}

}
