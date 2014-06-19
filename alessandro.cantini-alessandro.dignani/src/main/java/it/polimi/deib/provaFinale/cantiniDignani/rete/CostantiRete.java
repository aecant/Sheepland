package it.polimi.deib.provaFinale.cantiniDignani.rete;

public class CostantiRete {

	// TODO nella versione definitiva, impostare a 30000
	public static final long MILLISECONDI_TIMER_PARTITA = 5000;

	public static final String INDIRIZZO_SERVER = "localhost"; // pc di Cant a casa sua 192.168.11.4

	public static final int PORTA_SERVER_SOCKET = 12975;

	public static final int PORTA_SERVER_RMI = 12844;

	public static final String NOME_SERVER_RMI = "sheepland";

	public static int MOSSA_DISCONNESSIONE = Integer.MIN_VALUE;
	
	/**
	 * Costruttore private per nascondere quello di default
	 */
	private CostantiRete() {
	}

}
