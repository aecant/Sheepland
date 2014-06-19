package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
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
	 * @param giocatore
	 *            il giocatore a cui inviare l'evento
	 */
	void inviaEvento(Evento evento, Utente utente);

	/**
	 * Chiude la connessione
	 */
	void termina();

	/**
	 * Gestisce la disconnessione di un utente
	 * 
	 * @param utente
	 *            l'utente di cui va gestita la disconnessione
	 */
	void gestisciDisconnessione(Utente utente);
}
