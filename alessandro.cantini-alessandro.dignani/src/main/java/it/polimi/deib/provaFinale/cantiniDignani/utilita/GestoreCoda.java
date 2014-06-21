package it.polimi.deib.provaFinale.cantiniDignani.utilita;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestisce una coda di elementi del tipo passato come parametro.
 * 
 * @param <T>
 *            il tipo degli elementi della coda
 */

public class GestoreCoda<T> {
	
	private final static Logger logger = Logger.getLogger(GestoreCoda.class.getName());

	private BlockingQueue<T> coda = new LinkedBlockingQueue<T>();

	/**
	 * Restituisce il primo elemento nella coda. Se la coda non contiene
	 * elementi aspetta finche' un altro thread aggiunge un elemento alla coda.
	 * 
	 * @return
	 */
	public T aspetta() {
		T evento = null;
		try {
			evento = coda.take();
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "coda interrotta", e);
		}
		return evento;
	}

	/**
	 * Aspetta e restituisce un elemento di un determinato tipo, controlla che
	 * l'elemento ricevuto sia del tipo passato come parametro, in caso negativo
	 * lancia un eccezione
	 * 
	 * @param classe
	 *            il tipo di evento che ci si aspetta
	 * @return l'evento ricevuto dal server
	 */
	public T aspetta(Class<? extends T> classe) {
		T elemento = aspetta();
		if (elemento.getClass() != classe) {
			throw new IllegalArgumentException("L'elemento in coda è diverso da quello aspettato");
		}
		return elemento;
	}

	/**
	 * Aggiunge un elemento alla coda
	 * 
	 * @param elemento
	 *            l'elemento da aggiungere
	 */
	public void aggiungi(T elemento) {
		coda.add(elemento);
	}

	/**
	 * Elimina tutti gli elementi eventualmente presenti nella coda
	 */
	public void svuota() {
		coda.clear();
	}

	/**
	 * Controlla se la coda e' vuota
	 * 
	 * @return true se la coda e' vuota, false se contiene almeno un elemento
	 */
	public boolean codaVuota() {
		return coda.isEmpty();
	}

}
