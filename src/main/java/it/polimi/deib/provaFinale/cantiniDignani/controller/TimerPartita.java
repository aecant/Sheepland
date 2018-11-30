package it.polimi.deib.provaFinale.cantiniDignani.controller;

/**
 * Timer he fa iniziare la partita
 */
public class TimerPartita extends Timer {

	private ServerSheepland server;

	/**
	 * Crea un timer
	 * 
	 * @param millisecondi
	 *            i millisecondi dopo cui scade il timer
	 * @param server
	 *            il server su cui sta la partita
	 */
	public TimerPartita(long millisecondi, ServerSheepland server) {
		super(millisecondi);
		this.server = server;
	}

	@Override
	public void agisci() {
		server.iniziaPartita();
	}

}
