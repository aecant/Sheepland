package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilita {
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
	 * @param array
	 * @return
	 */
	public static <E> ArrayList<E> copia(ArrayList<E> array) {
		ArrayList<E> temp = new ArrayList<E>();
		temp.addAll(array);
		return temp;
	}
}
