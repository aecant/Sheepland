package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Timer extends Thread {

	private static final Logger LOGGER = Logger.getLogger(Timer.class.getName());

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
				LOGGER.log(Level.SEVERE, "Il timer e' stato interrotto", e);
			}
		}
	}

	/**
	 * L'azione da compiere quando il timer e' finito
	 */
	public abstract void agisci();

	/**
	 * Inizia e resetta il timer
	 */
	public synchronized void inizia() {
		inizio = System.currentTimeMillis();
		on = true;
	}

	/**
	 * Ferma il timer
	 */
	public synchronized void ferma() {
		on = false;
	}

	/**
	 * Termina il thread
	 */
	public synchronized void termina() {
		attivo = false;
	}
}
