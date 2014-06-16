package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

public interface InterfacciaConnessioneClient {

	/**
	 * Imposta la connessione con il server e cominicia a ricevere gli eventi
	 */
	void inizia();

	/**
	 * Registra il nome del giocatore sul server
	 * 
	 * @param nome
	 */
	void registraGiocatore(String nome) throws NomeGiaPresenteException;

	/**
	 * Invia una mossa al server
	 * 
	 * @param mossaScelta
	 *            a seconda della mossa, il codice della strada o di un
	 *            territorio, oppure l'indice di un elemento in una lista
	 *            passata dal server see{@InterfacciaUtente}
	 */
	void inviaMossa(int mossaScelta);

	/**
	 * Scarica dal server tutti i dati della partita
	 * 
	 * @return i dati della partita inviati dal server
	 */
	DatiPartita scaricaDatiPartita();

	void termina();

}
