package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilita {
	public static <E> boolean contieneDuplicati(List<E> array) {
		List<E> list = array;
		Set<E> set = new HashSet<E>(list);

		if(set.size() < list.size()){
		    return true;
		}
		return false;
	}
	
	public static boolean contieneDuplicati(Object[] array) {
		return contieneDuplicati(Arrays.asList(array));
	}
}
