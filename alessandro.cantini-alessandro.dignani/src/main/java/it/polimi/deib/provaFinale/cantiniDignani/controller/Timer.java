package it.polimi.deib.provaFinale.cantiniDignani.controller;

public abstract class Timer extends Thread {
	private final long tempoDaAspettare;
	private final long precisione;
	
	private boolean on = false;
	private long inizio;
	
	public Timer (long millisecondi, long precisione) {
		this.tempoDaAspettare = millisecondi;
		this.precisione = precisione;
	}
	
	public Timer (long millisecondi) {
		this(millisecondi, 100L);
	}
		
	@Override
	public void run() {
		while (true) {
			if (on) {
				if (System.currentTimeMillis() - inizio >= tempoDaAspettare) {
					agisci();
				}
			}
			try {
				Thread.sleep(precisione);
			} catch (InterruptedException e) {
				System.err.println("Il timer e' stato interrotto");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * L'azione da compiere quando il timer e' finito
	 */
	abstract public void agisci();

	/**
	 * Inizia e resetta il timer
	 */
	synchronized public void inizia() {
		inizio = System.currentTimeMillis();
		on = true;
	}
	
	/**
	 * Ferma il timer
	 */
	synchronized public void ferma() {
		on = false;
	}
}
