package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;

public abstract class ConnessioneServer extends Thread {
	
	protected final ServerSheepland serverSheepland;
	
	public ConnessioneServer(ServerSheepland serverSheepland) {
		this.serverSheepland = serverSheepland;
	}
	
	@Override
	public void run() {
		inizia();
	}
	
	public abstract void inizia();

	public ServerSheepland getServerSheepland() {
		return serverSheepland;
	}
}
