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
	public static final int SECONDI_INTERRUZIONE_DISCONNESSIONE = 30;

	/**
	 * Il numero che restituisce un client disconnesso se gli viene chiesta una
	 * mossa
	 */
	public static final int MOSSA_DISCONNESSIONE = Integer.MIN_VALUE;

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
	 * Il numero di convenzione che rappresenta la fine della scelta del
	 * giocatore nel market
	 */
	public static final int TERMINATORE_MARKET = -1;

	/**
	 * Il prezzo minimo a cui si puo' vendere una tessera
	 */
	public static final int MIN_PREZZO_MARKET = 1;

	/**
	 * Il prezzo massimo a cui si puo' vendere una tessera
	 */
	public static final int MAX_PREZZO_MARKET = 4;

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiController() {
	}
}
