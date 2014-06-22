package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Timer extends Thread {
	
	private final static Logger logger = Logger.getLogger(Timer.class.getName());
	
	private final long tempoDaAspettare;
	private final long precisione;

	private boolean attivo = true;
	private boolean on = false;
	private long inizio;


	public Timer(long millisecondi, long precisione) {
		this.tempoDaAspettare = millisecondi;
		this.precisione = precisione;
	}

	public Timer(long millisecondi) {
		this(millisecondi, 100L);
	}

	@Override
	public void run() {
		while (attivo) {
			while (on) {
				if (System.currentTimeMillis() - inizio >= tempoDaAspettare) {
					agisci();
				}
			}
			try {
				Thread.sleep(precisione);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, "Il timer e' stato interrotto", e);
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
	
	/**
	 * Termina il thread
	 */
	synchronized public void termina() {
		attivo = false;
	}
}
