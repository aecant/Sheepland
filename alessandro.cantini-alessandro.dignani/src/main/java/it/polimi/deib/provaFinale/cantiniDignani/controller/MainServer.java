package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Logger;

public class MainServer {

	private MainServer() {
	}
	
	public static void main(String[] args) {
		Logger.getLogger("").setLevel(CostantiTest.LIVELLO_LOGGER);
		
		ServerSheepland server = new ServerSheepland();
		server.inizia();
	}

}
