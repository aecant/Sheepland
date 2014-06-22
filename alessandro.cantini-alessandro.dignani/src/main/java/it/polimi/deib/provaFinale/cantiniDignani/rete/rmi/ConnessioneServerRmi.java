package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneServerRmi extends ConnessioneServer implements InterfacciaConnessioneServer {

	private final static Logger LOGGER = Logger.getLogger(ConnessioneServerRmi.class.getName());

	private final Map<String, InterfacciaAscoltatoreRmi> ascoltatori = new HashMap<String, InterfacciaAscoltatoreRmi>();
	private Registry registro;

	public ConnessioneServerRmi(ServerSheepland serverSheepland) {
		super(serverSheepland);
	}

	public void run() {
		inizia();
	}

	public void inizia() {
		try {
			InterfacciaRmi interfacciaMetodi = new InterfacciaRmiImpl(this, serverSheepland);
			InterfacciaRmi stub = (InterfacciaRmi) UnicastRemoteObject.exportObject(interfacciaMetodi, 0);
			registro = LocateRegistry.createRegistry(CostantiRmi.PORTA_SERVER_RMI);
			registro.rebind(CostantiRmi.NOME_SERVER_RMI, stub);
			
			new ControlloClientOnlineRmi(this).start();
			
			LOGGER.info("Server RMI pronto");
		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, "Problema nel creare il server RMI", e);
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {
		try {
			getAscoltatori().get(utente.getNome()).riceviEvento(evento);
		} catch (RemoteException e) {
			LOGGER.log(Level.FINE, "giocatore disconnesso", e);
			serverSheepland.gestisciDisconnessione(utente);
		}

	}

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(registro, true);
			registro.unbind(CostantiRmi.NOME_SERVER_RMI);
		} catch (NoSuchObjectException e) {
			LOGGER.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		} catch (RemoteException e) {
			LOGGER.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		} catch (NotBoundException e) {
			LOGGER.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		}
	}

	protected synchronized Map<String, InterfacciaAscoltatoreRmi> getAscoltatori() {
		return ascoltatori;
	}

	public void gestisciDisconnessione(Utente utente) {
		getAscoltatori().remove(utente.getNome());
	}

	@Override
	public String toString() {
		return "Connessione RMI";
	}

}
