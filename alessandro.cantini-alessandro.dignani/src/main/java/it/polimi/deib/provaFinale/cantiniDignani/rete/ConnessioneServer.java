package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;

public abstract class ConnessioneServer extends Thread {
	
	protected ServerSheepland serverSheepland;
	
	public ConnessioneServer(ServerSheepland server) {
		this.serverSheepland = server;
	}
	
	@Override
	public void run() {
		inizia();
	}
	
	public abstract void inizia();
}
