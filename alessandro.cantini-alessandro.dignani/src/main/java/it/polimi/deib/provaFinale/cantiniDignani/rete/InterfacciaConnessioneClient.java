package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

public interface InterfacciaConnessioneClient {

	/**
	 * Imposta la connessione con il server e cominicia a ricevere gli eventi
	 */
	void inizia();

	/**
	 * Registra il nome del giocatore sul server
	 * 
	 * @param nome
	 *            il nome da registrare
	 * @throws NomeGiaPresenteException
	 *             se c'e' un giocatore che sta giocando con lo stesso nome
	 * @throws PasswordSbagliataException
	 *             se esiste gia' un utente registrato con questo nome che non
	 *             sta giocando e la password non coincide
	 */
	void registraGiocatore(Coppia<String, String> nomeEPassword) throws NomeGiaPresenteException, PasswordSbagliataException;

	/**
	 * Invia una mossa al server
	 * 
	 * @param mossaScelta
	 *            a seconda della mossa, il codice della strada o di un
	 *            territorio, oppure l'indice di un elemento in una lista
	 *            passata dal server see{@InterfacciaUtente}
	 */
	void inviaMossa(Integer mossaScelta);

	/**
	 * Chiude la connessione
	 */
	void termina();

}
