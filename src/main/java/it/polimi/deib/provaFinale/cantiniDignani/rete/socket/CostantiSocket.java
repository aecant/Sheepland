package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

public class CostantiSocket {

	/**
	 * Il codice da comunicare al client se la registrazione ' andata a buon
	 * fine
	 */
	public static final char REGISTRAZIONE_OK = 's';
	/**
	 * Il codice da comunicare al client nel caso il nome sia gia' presente
	 */
	public static final char NOME_GIA_PRESENTE = 'n';
	/**
	 * Il codice da comunicare al client nel caso in cui la password inserita
	 * sia sbagliata
	 */
	public static final char PASSWORD_SBAGLIATA = 'p';

	/**
	 * La porta su cui viene aperto il server socket
	 */
	public static final int PORTA_SERVER_SOCKET = 12975;

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiSocket() {
	}

}
