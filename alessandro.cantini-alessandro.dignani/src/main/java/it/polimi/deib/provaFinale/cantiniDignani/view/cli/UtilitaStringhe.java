package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class UtilitaStringhe {

	/**
	 * Costruttore privato per nascondere quello pubblico di default
	 */
	private UtilitaStringhe() {
	}

	/**
	 * Crea una stringa composta dalle stringhe di una collezione.
	 * 
	 * @param collezione
	 *            la lista di stringhe
	 * @param separatore
	 *            i caratteri fra una stringa e un'altra
	 * @param fine
	 *            la stringa da aggiungere alla fine
	 * @return la stringa composta dalle stringhe in una collezione
	 */
	public static String listaDiStringhe(Collection<String> collezione, String separatore, String fine) {
		String listaStringhe = "";
		Iterator<String> iter = collezione.iterator();

		while (iter.hasNext()) {
			String s = iter.next();
			listaStringhe += s;
			if (iter.hasNext()) {
				listaStringhe += separatore;
			}
		}
		listaStringhe += fine;

		return listaStringhe;
	}
	
	public static String listaDiInteri(Collection<Integer> collezione, String separatore, String fine) {
		return listaDiStringhe(trasforma(collezione), separatore, fine);
	}

	public static String listaDiStringhe(String[] array, String separatore, String fine) {
		return listaDiStringhe(Arrays.asList(array), separatore, fine);
	}

	public static String daA(int origine, int destinazione, boolean fineFrase) {
		String fine = fineFrase ? "." : "";
		return "da " + origine + " a " + destinazione + fine;
	}

	public static String daA(int origine, int destinazione) {
		return daA(origine, destinazione, false);
	}

	public static String inizialeMaiuscola(String s) {
		s = s.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String nelTerr(int territorio, boolean fineFrase) {
		String fine = fineFrase ? "." : "";
		return "nel territorio " + territorio + fine;
	}
	
	public static String nelTerr(int territorio) {
		return nelTerr(territorio, false);
	}
	
	public static Collection<String> trasforma(Collection<Integer> collInteri){
		Collection<String> collStringhe = new ArrayList<String>();
		for(Integer i : collInteri) {
			collStringhe.add(i.toString());
		}
		return collStringhe;
	}

}
