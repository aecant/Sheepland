package it.polimi.deib.provaFinale.cantiniDignani.controller;


/**
 *	Timer che notifica un oggetto Runnable passato nel costruttore
 */
public class TimerNotify extends Timer {

	private Runnable daNotificare;

	public TimerNotify(long millisecondi, Runnable daNotificare) {
		super(millisecondi);
		this.daNotificare = daNotificare;
	}
	
	@Override
	public void agisci() {
		synchronized(daNotificare) {
			daNotificare.notify();
		}
	}

}
