package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {

	private MainServer() {
	}

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.ALL);

		ServerSheepland server = new ServerSheepland();
		server.inizia();
	}

}
