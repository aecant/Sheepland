package it.polimi.deib.provaFinale.cantiniDignani.rete;

import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

public interface InterfacciaServer {

	public void inizializza();

	/**
	 * Invia un evento a determinati giocatori
	 * 
	 * @param evento
	 *            l'evento da inviare
	 * @param giocatori
	 *            la lista dei giocatori a cui inviare l'evento
	 */
	public void inviaEvento(Evento evento, List<String> giocatori);

}
