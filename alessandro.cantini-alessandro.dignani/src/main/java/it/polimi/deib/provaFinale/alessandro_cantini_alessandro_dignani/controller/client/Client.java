package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.client;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server.Server;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.ConnessioneRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", Server.SERVER_PORT);
			ConnessioneRMI connessione = (ConnessioneRMI) registry.lookup(Server.SERVER_NAME);
			
			System.out.println(connessione.getTessereConfinanti(0, 7)[0]);
			System.out.println(connessione.getTessereConfinanti(0, 7)[1]);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
