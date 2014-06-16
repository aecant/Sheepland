package it.polimi.deib.provaFinale.cantiniDignani.rete;

public class CostantiRete {
	
	//TODO nella versione definitiva, impostare a 30000
	public static final long MILLISECONDI_TIMER_PARTITA = 5000;

	public static final String INDIRIZZO_SERVER = "127.0.0.1";

	public static final int PORTA_SERVER = 12844;

	public static final String NOME_SERVER = "sheepland";

	//variabile che serve solo nei test
	public static final boolean RMI = false;
	
	/**
	 * Costruttore private per nascondere quello di default
	 */
	private CostantiRete() {
	}
}
