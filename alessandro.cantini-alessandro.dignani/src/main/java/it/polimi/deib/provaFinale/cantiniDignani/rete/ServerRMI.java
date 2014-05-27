package it.polimi.deib.provaFinale.cantiniDignani.rete;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements InterfacciaServer {

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
	
}
