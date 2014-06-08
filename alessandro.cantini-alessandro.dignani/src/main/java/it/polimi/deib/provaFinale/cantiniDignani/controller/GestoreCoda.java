package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Gestisce una coda del tipo passato come parametro.
 * 
 * @param <T>
 *            il tipo degli elementi della coda
 */

public class GestoreCoda<T> {

	private BlockingQueue<T> codaEventi = new LinkedBlockingQueue<T>();

	/**
	 * Aspetta e restituisce un evento inviato dal server
	 * 
	 * @return l'evento ricevuto dal server
	 */
	public T aspettaEvento() {
		T evento = null;
		try {
			evento = codaEventi.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		T elemento = aspettaEvento();
		if (elemento.getClass() != classe) {
			throw new RuntimeException("L'evento è diverso da quello aspettato");
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
		codaEventi.add(elemento);
	}

}
