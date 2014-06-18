package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class ConnessioneServerRmi extends Thread implements InterfacciaConnessioneServer{

	private final Hashtable<String, AscoltatoreEventiRmi> ascoltatori = new Hashtable<String, AscoltatoreEventiRmi>();
	private Registry registro;

	public void run() {
		inizia();
	}
	
	public void inizia() {
		try {
			InterfacciaRmi server = new InterfacciaRmiImpl(this);
			InterfacciaRmi stub = (InterfacciaRmi) UnicastRemoteObject.exportObject(server, 0);
			registro = LocateRegistry.createRegistry(CostantiRete.PORTA_SERVER_RMI);
			registro.rebind(CostantiRete.NOME_SERVER_RMI, stub);

			ServerMain.LOGGER.println("Server RMI pronto");
		} catch (RemoteException e) {
			System.err.println("Eccezione server RMI:");
			e.printStackTrace();
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {
		try {
			ServerMain.LOGGER.println("Evento inviato a " + utente + ": " + evento);
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

	protected Hashtable<String, AscoltatoreEventiRmi> getAscoltatori() {
		return ascoltatori;
	}

	@Override
	public String toString() {
		return "Connessione RMI";
	}

}
