package it.polimi.deib.provaFinale.cantiniDignani.rete;

import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

public interface InterfacciaConnessioneServer {

	/**
	 * Inizializza il server e comincia ad accettare le connessioni in ingresso
	 */
	void inizia();

	/**
	 * Invia un evento a determinati giocatori
	 * 
	 * @param evento
	 *            l'evento da inviare
	 * @param giocatori
	 *            la lista dei giocatori a cui inviare l'evento
	 */
	void inviaEvento(Evento evento, List<String> giocatori);

	/**
	 * Chiude la connessione
	 */
	void termina();

}
