package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	 * Controlla se un array contiene un certo elementeo
	 * 
	 * @param array
	 *            l'array da analizzare
	 * @param element
	 *            l'elemento da cercare
	 * @return true se l'array contiene l'elemento, false altrimenti
	 */
	public static <E> boolean contiene(E[] array, E element) {
		return Arrays.asList(array).contains(element);
	}

	/**
	 * Restituisce una copia di un ArrayList. Si tratta di una copia di
	 * superficie, in modo tale che non si possano aggiungere ne' rimuovere
	 * elementi dalla lista; invece i singoli elementi si possono modificare
	 * 
	 * @param lista
	 *            l'arrayList da copiare
	 * @return una copia dell'ArrayList passato come parametro
	 */
	public static <E> List<E> copia(List<E> lista) {
		List<E> temp = new ArrayList<E>();
		temp.addAll(lista);
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

	/**
	 * Incrementa il valore di una certa chiave in una mappa che associa un
	 * intero a un tipo generico. Se la chiave non e' presente nella mappa viene
	 * aggiunta e il relativo valore viene inizializzato alla quantita passata
	 * come parametro
	 * 
	 * @param mappa
	 *            la mappa che associa un intero a un valore
	 * @param chiave
	 *            la chiave di cui aumentare il valore
	 * @param quantita
	 *            la quantita di cui aumentare il valore
	 * 
	 */
	public static <K> void incrementa(Map<K, Integer> mappa, K chiave, Integer quantita) {
		mappa.put(chiave, mappa.getOrDefault(chiave, 0) + quantita);
	}

	/**
	 * Incrementa di uno il valore di una certa chiave in una mappa che associa
	 * un intero a un tipo generico. Se la chiave non e' presente nella mappa
	 * viene aggiunta e il relativo valore viene inizializzato alla quantita
	 * passata come parametro
	 * 
	 * @param mappa
	 *            la mappa che associa un intero a un valore
	 * @param chiave
	 *            la chiave di cui aumentare il valore
	 */
	public static <K> void incrementa(Map<K, Integer> mappa, K chiave) {
		incrementa(mappa, chiave, 1);
	}

}
