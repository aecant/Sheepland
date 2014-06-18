package it.polimi.deib.provaFinale.cantiniDignani.controller;

public class TimerPartita extends Timer {

	private ServerSheepland server;

	public TimerPartita(long millisecondi, ServerSheepland server) {
		super(millisecondi);
		this.server = server;
	}

	@Override
	public void agisci() {
		server.iniziaPartita();
	}
	
}
