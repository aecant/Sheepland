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
	 * Restituisc una stringa composta dalle stringhe di una collezione.
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

	/**
	 * Restituisce una lista formattata di stringhe
	 * 
	 * @param array
	 *            l'array di stringhe da formattare
	 * @param separatore
	 *            la stringa fra un elemento e il successivo
	 * @param fine
	 *            la stringa da appendere alla fine
	 * @return una lista formattata di stringhe
	 */
	public static String listaDiStringhe(String[] array, String separatore, String fine) {
		return listaDiStringhe(Arrays.asList(array), separatore, fine);
	}

	/**
	 * Restituisce una stringa formattata rappresentante una lista di interi
	 * 
	 * @param collezione
	 *            la collezione di stringhe da formattare
	 * @param separatore
	 *            la stringa fra un elemento e il successivo
	 * @param fine
	 *            la stringa da appendere alla fine
	 * @return una formattata lista di interi
	 */
	public static String listaDiInteri(Collection<Integer> collezione, String separatore, String fine) {
		return listaDiStringhe(trasformaInStringhe(collezione), separatore, fine);
	}

	/**
	 * Restituisce una stringa formattata rappresentante una lista di interi
	 * 
	 * @param collezione
	 *            la collezione di stringhe da formattare
	 * @return una formattata lista di interi
	 */
	public static String listaDiInteri(Collection<Integer> collezione) {
		return listaDiInteri(collezione, ", ", ".");
	}

	/**
	 * Restituisce una stringa "da...a..." formattata per bene
	 * 
	 * @param origine
	 *            da..
	 * @param destinazione
	 *            a..
	 * @param fineFrase
	 *            stringa da aggiungere alla fine
	 * @return una stringa "da...a..." formattata per bene con una stringa
	 *         concatenata alla fine
	 */
	public static String daA(int origine, int destinazione, String fine) {
		return "da " + origine + " a " + destinazione + fine;
	}

	/**
	 * Restituisce una stringa "da...a..." formattata per bene
	 * 
	 * @param origine
	 *            da..
	 * @param destinazione
	 *            a..
	 * @return una stringa formattata "da...a..."
	 */
	public static String daA(int origine, int destinazione) {
		return daA(origine, destinazione, "");
	}

	/**
	 * Data una stringa, la restituisce con l'iniziale maiuscola e le altre
	 * lettere minuscole
	 * 
	 * @param s
	 *            la stringa da formattare
	 * @return una stringa con l'iniziale maiuscola
	 */
	public static String inizialeMaiuscola(String s) {
		s = s.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * Restituisce una stringa formattata del complemento di luogo verso un
	 * territorio
	 * 
	 * @param territorio
	 *            il codice del territorio
	 * @param fineFrase
	 *            la stringa da appendere alla fine
	 * @return un complemento di luogo ben formattato
	 */
	public static String nelTerr(int territorio, String fine) {
		return "nel territorio " + territorio + fine;
	}

	/**
	 * Restituisce una stringa formattata del complemento di luogo verso un
	 * territorio
	 * 
	 * @param territorio
	 *            il codice del territorio
	 * @return un complemento di luogo ben formattato
	 */
	public static String nelTerr(int territorio) {
		return nelTerr(territorio, "");
	}

	/**
	 * Trasforma una collezione di interi in una collezione di stringhe
	 * 
	 * @param collInteri
	 *            la collezione di interi da trasformare
	 * @return la collezione di stringhe trasformata
	 */
	public static Collection<String> trasformaInStringhe(Collection<Integer> collInteri) {
		Collection<String> collStringhe = new ArrayList<String>();
		for (Integer i : collInteri) {
			collStringhe.add(i.toString());
		}
		return collStringhe;
	}

	/**
	 * Restituisce un menu per scegliere un elemento di una lista. Utile
	 * specialmente se utilizzato con @link {InputCli#scegliElemento}
	 * 
	 * @param lista
	 *            la lista di elementi fra cui scegliere
	 * @param separatore
	 *            la stringa fra il numero e la scelta
	 * @param fineRiga
	 *            la stringa alla fine delle righe
	 * @param fineElenco
	 *            la stringa alla fine dell'ultima riga
	 * @param indice
	 *            l'indice da cui far partire l'elenco
	 * @return un menu di scelta con gli elementi di una lista
	 */
	public static <E> String menuDiScelta(Collection<E> lista, String separatore, String fineRiga, String fineElenco, int indice) {
		String elenco = "";

		Iterator<E> iter = lista.iterator();
		while (iter.hasNext()) {
			String elem = iter.next().toString();
			String fine = iter.hasNext() ? fineRiga : fineElenco;
			elenco += indice + separatore + elem + fine;
			indice++;
		}

		return elenco;
	}

	/**
	 * Restituisce un menu per scegliere un elemento di una lista. Utile
	 * specialmente se utilizzato con @link {InputCli#scegliElemento}
	 * 
	 * @param lista
	 *            la lista di elementi fra cui scegliere
	 * @param separatore
	 *            la stringa fra il numero e la scelta
	 * @param fineRiga
	 *            la stringa alla fine delle righe
	 * @param fineElenco
	 *            la stringa alla fine dell'ultima riga
	 * @return un menu di scelta con gli elementi di una lista
	 */
	public static <E> String menuDiScelta(Collection<E> lista, String separatore, String fineRiga, String fineElenco) {
		return menuDiScelta(lista, separatore, fineRiga, fineElenco, 1);
	}

	/**
	 * Restituisce un menu per scegliere un elemento di una lista. Utile
	 * specialmente se utilizzato con @link {InputCli#scegliElemento}
	 * 
	 * @param lista
	 *            la lista di elementi fra cui scegliere
	 * 
	 * @param indice
	 *            la lista di elementi fra cui scegliere
	 * @return un menu di scelta con gli elementi di una lista
	 */
	public static <E> String menuDiScelta(Collection<E> lista, int indice) {
		return menuDiScelta(lista, ") ", ";\n", ".", indice);
	}

	/**
	 * Restituisce un menu per scegliere un elemento di una lista
	 * 
	 * @param lista
	 *            la lista di elementi fra cui scegliere
	 * @return un menu di scelta con gli elementi di una lista
	 */
	public static <E> String menuDiScelta(Collection<E> lista) {
		return menuDiScelta(lista, 1);
	}

}
