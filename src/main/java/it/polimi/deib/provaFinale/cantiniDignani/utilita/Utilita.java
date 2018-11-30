package it.polimi.deib.provaFinale.cantiniDignani.utilita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilita {

	private static final Logger LOGGER = Logger.getLogger(Utilita.class.getName());

	/**
	 * Costruttore privato per nascondere quello pubblico
	 */
	private Utilita() {
	}

	/**
	 * Restituisce una copia di una collezione. Si tratta di una copia di
	 * superficie, in modo tale che non si possano aggiungere ne' rimuovere
	 * elementi dalla lista; invece i singoli elementi si possono modificare
	 * 
	 * @param lista
	 *            l'arrayList da copiare
	 * @return una copia dell'ArrayList passato come parametro
	 */
	public static <E> Collection<E> copia(Collection<E> coll) {
		List<E> temp = new ArrayList<E>();
		temp.addAll(coll);
		return temp;
	}

	/**
	 * Controlla se una lista contiene duplicati
	 * 
	 * @param lista
	 *            la lista da analizzare
	 * @return true se la lista contiene elementi duplicati, false altrimenti
	 */
	public static <E> boolean contieneDuplicati(Collection<E> lista) {
		Collection<E> list = lista;
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
		if (!mappa.containsKey(chiave)) {
			mappa.put(chiave, 0);
		}
		mappa.put(chiave, mappa.get(chiave) + quantita);
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

	/**
	 * Restituisce la lista degli indici dei valori true di un array di boolean
	 * 
	 * @param array
	 *            l'array di boolean
	 * @return la lista degli indici che contengono valori true
	 */
	public static List<Integer> indiciTrue(boolean[] array) {
		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				lista.add(i);
			}
		}
		return lista;
	}

	/**
	 * Restituisce una versione serializzabile di una collezione. Se la
	 * collezione e' gia' serializzabile, viene restituita senza essere
	 * modificata
	 * 
	 * @param collezione
	 *            la collezione da rendere serializzabile
	 * @return una versione serializzabile della collezione passata come
	 *         parametro
	 */
	public static <E> Collection<E> rendiSerializzabile(Collection<E> collezione) {
		if (collezione instanceof Serializable) {
			return collezione;
		}

		Collection<E> al = new ArrayList<E>();

		// prova il cast, se fallisce copia gli elementi uno a uno
		try {
			al = (ArrayList<E>) collezione;
		} catch (ClassCastException exc) {
			LOGGER.log(Level.FINE, "impossibile convertire la collezione in lista", exc);

			for (E elemento : collezione) {
				al.add(elemento);
			}
		}

		return al;
	}

	/**
	 * Restituisce un hash code per un oggetto usando una sola variabile stringa
	 * 
	 * @param stringa
	 *            la stringa con cui generare l'hashcode
	 * @return l'hash code generato da un singolo attributo stringa
	 */
	public static int hashCodeNome(String stringa) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stringa == null) ? 0 : stringa.hashCode());
		return result;
	}

}
