package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.util.logging.Logger;

public class Utente {
	private static final Logger LOGGER = Logger.getLogger(Utente.class.getName());

	private final String nome;
	private final String password;
	private final GestoreCoda<Integer> codaMosse;
	private InterfacciaConnessioneServer connessione;

	/**
	 * Crea un istanza di utente
	 * 
	 * @param nome
	 *            il nome dell'utente
	 * @param password
	 *            la password dell'utente
	 * @param connessione
	 *            la connessione dell'utente (RMI o socket per esempio)
	 */
	public Utente(String nome, String password, InterfacciaConnessioneServer connessione) {
		this.nome = nome;
		this.password = password;
		this.connessione = connessione;
		codaMosse = new GestoreCoda<Integer>();
	}

	/**
	 * Invia un evento all'utente
	 * 
	 * @param evento
	 */
	public void inviaEvento(Evento evento) {
		if (connessione != null) {
			connessione.inviaEvento(evento, this);
			LOGGER.info(evento + " inviato " + " a " + this);
		}
	}

	/**
	 * Restituisce il nome dell'utente
	 * 
	 * @return il nome dell'utente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce la password dell'utente
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Restituisce il gestore della coda
	 * 
	 * @return la password dell'utente
	 */
	public GestoreCoda<Integer> getCodaMosse() {
		return codaMosse;
	}

	/**
	 * Restituisce la connessione dell'utente
	 * 
	 * @return la connessione del'utente
	 */
	public InterfacciaConnessioneServer getConnessione() {
		return connessione;
	}

	/**
	 * Imposta la connessione dell'utente
	 * 
	 * @param connessione
	 *            la connessione da impostare
	 */
	public void setConnessione(InterfacciaConnessioneServer connessione) {
		this.connessione = connessione;
	}

	/**
	 * Restituisce l'hash code dell'utente
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Compara l'utente con un altro oggetto, utilizzando esclusivamente il nome
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Utente)) {
			return false;
		}
		Utente other = (Utente) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	/**
	 * Restituisce una rappresentazione dell'utente sotto forma di stringa
	 */
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", password=" + password + ", connessione=" + connessione + "]";
	}

}
