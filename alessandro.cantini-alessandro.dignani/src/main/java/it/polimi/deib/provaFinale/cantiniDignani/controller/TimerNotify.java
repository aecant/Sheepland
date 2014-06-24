package it.polimi.deib.provaFinale.cantiniDignani.controller;

/**
 * Timer che notifica un oggetto Runnable passato nel costruttore
 */
public class TimerNotify extends Timer {

	private Runnable daNotificare;

	/**
	 * Crea un timer
	 * 
	 * @param millisecondi
	 *            i millisecondi dopo i quali il timer scade
	 * @param daNotificare
	 *            l'oggetto da notificare quando scade il timer
	 */
	public TimerNotify(long millisecondi, Runnable daNotificare) {
		super(millisecondi);
		this.daNotificare = daNotificare;
	}

	/**
	 * Notifica un oggetto
	 */
	@Override
	public void agisci() {
		synchronized (daNotificare) {
			daNotificare.notify();
		}
	}

}
