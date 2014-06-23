package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneClientRmi extends ConnessioneClient implements InterfacciaConnessioneClient, InterfacciaAscoltatoreRmi {

	private static final String NO_CONNESSIONE = "Impossibile stabilire la connessione col server";

	private static final Logger LOGGER = Logger.getLogger(ConnessioneClientRmi.class.getName());

	private InterfacciaRmi server;
	private InterfacciaAscoltatoreRmi ascoltatore;

	public ConnessioneClientRmi(String indirizzoServer, GestoreCoda<Evento> codaEventi) {
		super(indirizzoServer, codaEventi);
	}

	public void inizia() {
		try {
			Registry registry = LocateRegistry.getRegistry(getIndirizzoServer(), CostantiRmi.PORTA_SERVER_RMI);
			server = (InterfacciaRmi) registry.lookup(CostantiRmi.NOME_SERVER_RMI);

		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, NO_CONNESSIONE, e);
		} catch (NotBoundException e) {
			LOGGER.log(Level.SEVERE, CostantiRmi.NOME_SERVER_RMI + " non collegato ", e);
		}
	}

	public void registraGiocatore(Coppia<String, String> nomeEPassword) throws NomeGiaPresenteException, PasswordSbagliataException {
		try {
			server.registraGiocatore(nomeEPassword.getPrimo(), nomeEPassword.getSecondo());
			ascoltatore = (InterfacciaAscoltatoreRmi) UnicastRemoteObject.exportObject(this, 0);
			server.aggiungiAscoltatore(nomeEPassword.getPrimo(), ascoltatore);
		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, NO_CONNESSIONE, e);
		}

	}

	public void riceviEvento(Evento evento) throws RemoteException {
		getCodaEventi().aggiungi(evento);
	}

	public boolean seiOnline() throws RemoteException {
		return true;
	}

	public void inviaMossa(Integer mossaScelta) {
		try {
			server.riceviMossa(MainClient.getNome(), mossaScelta);
		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, NO_CONNESSIONE, e);
		}
	}

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(ascoltatore, true);
		} catch (NoSuchObjectException e) {
			LOGGER.log(Level.SEVERE, NO_CONNESSIONE, e);
		}
	}

}
