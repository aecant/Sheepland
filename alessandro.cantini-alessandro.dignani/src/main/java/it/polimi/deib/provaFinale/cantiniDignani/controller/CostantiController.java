package it.polimi.deib.provaFinale.cantiniDignani.controller;

public class CostantiController {

	// TODO nella versione definitiva, impostare a 30000
	/**
	 * Il numero di millisecondi per cui aspettare che si connettano altri
	 * utenti prima di iniziare la partita
	 */
	public static final long MILLISECONDI_TIMER_PARTITA = 5000;

	/**
	 * Il numero di millisecondi per cui sospendere una partita nel caso di
	 * disconnessione di un giocatore
	 */
	public static final long MILLISECONDI_INTERRUZIONE_DISCONNESSIONE = 30000;

	/**
	 * Il numero che restituisce un client disconnesso se gli viene chiesta una
	 * mossa
	 */
	public static int MOSSA_DISCONNESSIONE = Integer.MIN_VALUE;

	/**
	 * La somma di denaro da pagare dopo un abbattimento per il silenzio di un
	 * pastore
	 */
	public static final int COSTO_SILENZIO = 2;

	/**
	 * Il risultato minimo per ottenere un risarciment dopo un abbattimento
	 */
	public static final int DADO_MIN_PER_SILENZIO = 5;

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiController() {
	}
}
