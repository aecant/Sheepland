package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {

	public static void main(String[] args) {
		Logger.getLogger("").setLevel(Level.WARNING);
		
		ServerSheepland server = new ServerSheepland();
		server.inizia();
	}

}
