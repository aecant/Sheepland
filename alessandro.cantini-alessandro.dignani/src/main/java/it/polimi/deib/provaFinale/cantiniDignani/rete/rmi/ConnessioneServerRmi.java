package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class ConnessioneServerRmi extends ConnessioneServer implements InterfacciaConnessioneServer {

	private final Hashtable<String, InterfacciaAscoltatoreRmi> ascoltatori = new Hashtable<String, InterfacciaAscoltatoreRmi>();
	private Registry registro;

	public ConnessioneServerRmi(ServerSheepland server) {
		super(server);
	}

	public void run() {
		inizia();
	}

	public void inizia() {
		try {
			InterfacciaRmi interfacciaMetodi = new InterfacciaRmiImpl(this, serverSheepland);
			InterfacciaRmi stub = (InterfacciaRmi) UnicastRemoteObject.exportObject(interfacciaMetodi, 0);
			registro = LocateRegistry.createRegistry(CostantiRete.PORTA_SERVER_RMI);
			registro.rebind(CostantiRete.NOME_SERVER_RMI, stub);

			ServerSheepland.LOGGER.println("Server RMI pronto");
		} catch (RemoteException e) {
			System.err.println("Eccezione server RMI:");
			e.printStackTrace();
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {
		try {
			ascoltatori.get(utente.getNome()).riceviEvento(evento);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(registro, true);
			registro.unbind(CostantiRete.NOME_SERVER_RMI);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Hashtable<String, InterfacciaAscoltatoreRmi> getAscoltatori() {
		return ascoltatori;
	}

	@Override
	public String toString() {
		return "Connessione RMI";
	}

	public void gestisciDisconnessione(Utente utente) {
		ascoltatori.remove(utente.getNome());
	}

}
