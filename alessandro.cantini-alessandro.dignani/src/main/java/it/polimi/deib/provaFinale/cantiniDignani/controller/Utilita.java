package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilita {
	private Utilita() {
	}

	public static <E> boolean contieneDuplicati(List<E> lista) {
		List<E> list = lista;
		Set<E> set = new HashSet<E>(list);

		if (set.size() < list.size()) {
			return true;
		}
		return false;
	}

	public static <E> boolean contieneDuplicati(E[] array) {
		return contieneDuplicati(Arrays.asList(array));
	}

	public static <E> boolean contiene(List<E> lista, E element) {
		for (E i : lista)
			if (i == element)
				return true;
		return false;
	}

	public static <E> boolean contiene(E[] array, E element) {
		return contiene(Arrays.asList(array), element);
	}

	/**
	 * Restituisce una copia di un ArrayList
	 * 
	 * @param array
	 * @return
	 */
	public static <E> ArrayList<E> copia(List<E> array) {
		ArrayList<E> temp = new ArrayList<E>();
		temp.addAll(array);
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
