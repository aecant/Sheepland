package it.polimi.deib.provaFinale.cantiniDignani.controller;

public class TimerPartita extends Timer {

	public TimerPartita(long millisecondi, long precisione) {
		super(millisecondi, precisione);
	}

	public TimerPartita(long millisecondi) {
		super(millisecondi);
	}

	@Override
	public void agisci() {
		ServerMain.iniziaPartita();
	}
	
}
