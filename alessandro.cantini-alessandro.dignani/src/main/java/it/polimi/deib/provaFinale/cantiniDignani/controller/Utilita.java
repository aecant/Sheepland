package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilita {
	/**
	 * Costruttore privato per nascondere quello pubblico
	 */
	private Utilita() {
	}

	/**
	 * Controlla se una lista contiene duplicati
	 * 
	 * @param lista
	 *            la lista da analizzare
	 * @return true se la lista contiene elementi duplicati, false altrimenti
	 */
	public static <E> boolean contieneDuplicati(List<E> lista) {
		List<E> list = lista;
		Set<E> set = new HashSet<E>(list);

		if (set.size() < list.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Controlla se un array contiene duplicati
	 * 
	 * @param array
	 *            l'array da analizzare
	 * @return true se l'array contiene elementi duplicati, false altrimenti
	 */
	public static <E> boolean contieneDuplicati(E[] array) {
		return contieneDuplicati(Arrays.asList(array));
	}

	/**
	 * Controlla se una lista contiene un certo elementeo
	 * 
	 * @param lista
	 *            la lista da analizzare
	 * @param element
	 *            l'elemento da cercare
	 * @return true se la lista contiene l'elemento, false altrimenti
	 */
	public static <E> boolean contiene(List<E> lista, E element) {
		for (E i : lista) {
			if (i == element) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Controlla se un array contiene un certo elementeo
	 * 
	 * @param array
	 *            l'array da analizzare
	 * @param element
	 *            l'elemento da cercare
	 * @return true se l'array contiene l'elemento, false altrimenti
	 */
	public static <E> boolean contiene(E[] array, E element) {
		return contiene(Arrays.asList(array), element);
	}

	/**
	 * Restituisce una copia di un ArrayList
	 * 
	 * @param al
	 *            l'arrayList da copiare
	 * @return una copia dell'ArrayList passato come parametro
	 */
	//TODO implementare come deep copy
	public static <E> ArrayList<E> copia(List<E> al) {
		ArrayList<E> temp = new ArrayList<E>();
		temp.addAll(al);
		return temp;
	}

	/**
	 * Sospende il thread per un certo numero di millisecondi
	 * 
	 * @param millisecondi
	 *            il numero di millisecondi per cui viene sospeso il thread
	 */
	public static void aspetta(long millisecondi) {
		try {
			Thread.sleep(millisecondi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
