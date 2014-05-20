package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.InterfacciaRMI;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.InterfacciaRMIImpl;


public class Server {

	public final static String SERVER_NAME = "sheepland";
	public final static int SERVER_PORT = 1218;

	public static void main(String[] args) {
		try {
			InterfacciaRMI dati = new InterfacciaRMIImpl();
			InterfacciaRMI stub = (InterfacciaRMI) UnicastRemoteObject.exportObject(dati, 0);
			Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
			registry.rebind(SERVER_NAME, stub);
			
			System.out.println("ComputeEngine bound, now accepting requests.");
			
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:");
			e.printStackTrace();}
	}
}
