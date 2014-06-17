package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Richiesta;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.List;

public class ServerRmi implements InterfacciaConnessioneServer {

	private final Hashtable<String, AscoltatoreEventiRmi> ascoltatori = new Hashtable<String, AscoltatoreEventiRmi>();
	private Registry registro;
	private final GestoreCoda<Integer> codaMosse;

	public ServerRmi(GestoreCoda<Integer> codaMosse) {
		this.codaMosse = codaMosse;
	}
	
	public void inizia() {
		try {
			InterfacciaRmi server = new InterfacciaRmiImpl(ascoltatori, codaMosse);
			InterfacciaRmi stub = (InterfacciaRmi) UnicastRemoteObject.exportObject(server, 0);
			registro = LocateRegistry.createRegistry(CostantiRete.PORTA_SERVER);
			registro.rebind(CostantiRete.NOME_SERVER, stub);

			ServerMain.LOGGER.println("Server RMI inizializzato, ora accetto le richieste.");
		} catch (RemoteException e) {
			System.err.println("Eccezione server RMI:");
			e.printStackTrace();
		}
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

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(registro, true);
			registro.unbind(CostantiRete.NOME_SERVER);
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

}
