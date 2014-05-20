package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.client;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server.Server;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.ConnessioneDatiPartita;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", Server.SERVER_PORT);
			ConnessioneDatiPartita connessione = (ConnessioneDatiPartita) registry.lookup(Server.SERVER_NAME);
			
			System.out.println(connessione.getTessereConfinanti(0, 8)[0]);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
