package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ServerRMI implements InterfacciaServer {

	private static Hashtable<String, AscoltatoreRemoto> ascoltatori = new Hashtable<String, AscoltatoreRemoto>();

	public void inizializza() {
		try {
			InterfacciaRMI server = new InterfacciaRMIImpl();
			InterfacciaRMI stub = (InterfacciaRMI) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(Costanti.SERVER_PORT);
			registry.rebind(Costanti.SERVER_NAME, stub);

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
		if(giocatori.size() == 0)
			throw new IllegalArgumentException("La lista dei giocatori non puo' essere vuota");
		for(String giocatore : giocatori) {
			try {
				ascoltatori.get(giocatore).riceviEvento(evento);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void inviaEvento(Evento evento, String giocatore) {
		List<String> listaDiUnElemento = new ArrayList<String>();
		listaDiUnElemento.add(giocatore);
		inviaEvento(evento, listaDiUnElemento);
	}

	public int aspettaPosizionePastore(String nome) {
		// TODO Auto-generated method stub
		return 0;
	}

}
