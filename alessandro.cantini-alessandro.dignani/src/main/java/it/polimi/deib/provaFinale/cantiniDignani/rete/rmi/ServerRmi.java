package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Richiesta;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.List;

public class ServerRmi implements InterfacciaServer {

	private static Hashtable<String, AscoltatoreRemoto> ascoltatori = new Hashtable<String, AscoltatoreRemoto>();

	public void inizializza() {
		try {
			InterfacciaRmi server = new InterfacciaRmiImpl();
			InterfacciaRmi stub = (InterfacciaRmi) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(CostantiRete.SERVER_PORT);
			registry.rebind(CostantiRete.SERVER_NAME, stub);

			System.out.println("Server RMI inizializzato, ora accetto le richieste.");
		} catch (RemoteException e) {
			System.err.println("Eccezione server RMI:");
			e.printStackTrace();
		}
	}

	public static Hashtable<String, AscoltatoreRemoto> getAscoltatori() {
		return ascoltatori;
	}

	public void inviaEvento(Evento evento, List<String> giocatori) {
		if (giocatori.size() == 0)
			throw new IllegalArgumentException("La lista dei giocatori non puo' essere vuota");
		if (evento instanceof Richiesta && giocatori.size() > 1) {
			throw new IllegalArgumentException("Non si possono mandare richieste a piu' giocatori");
		}
		for (String giocatore : giocatori) {
			try {
				ServerMain.LOGGER.println("Evento inviato a " + giocatore + ": " + evento);
				ascoltatori.get(giocatore).riceviEvento(evento);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
