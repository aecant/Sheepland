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
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneServerRmi extends ConnessioneServer implements InterfacciaConnessioneServer {

	private final Logger logger = Logger.getLogger(ConnessioneServerRmi.class.getName());

	private final Hashtable<String, InterfacciaAscoltatoreRmi> ascoltatori = new Hashtable<String, InterfacciaAscoltatoreRmi>();
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

			logger.info("Server RMI pronto");
		} catch (RemoteException e) {
			logger.log(Level.SEVERE, "Problema nel creare il server RMI", e);
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {
		try {
			ascoltatori.get(utente.getNome()).riceviEvento(evento);
		} catch (RemoteException e) {
			serverSheepland.gestisciDisconnessione(utente);
		}

	}

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(registro, true);
			registro.unbind(CostantiRmi.NOME_SERVER_RMI);
		} catch (NoSuchObjectException e) {
			logger.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		} catch (RemoteException e) {
			logger.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		} catch (NotBoundException e) {
			logger.log(Level.SEVERE, "Problema nella disconnessione del server", e);
		}
	}

	protected Hashtable<String, InterfacciaAscoltatoreRmi> getAscoltatori() {
		return ascoltatori;
	}

	public void gestisciDisconnessione(Utente utente) {
		ascoltatori.remove(utente.getNome());
	}

	@Override
	public String toString() {
		return "Connessione RMI";
	}

}
