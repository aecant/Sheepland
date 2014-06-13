package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class InputCli {
	private final Scanner in;
	private final PrintStream out = CostantiCli.DEFAULT_OUTPUT;

	public InputCli(InputStream inputStream) {
		in = new Scanner(inputStream);
	}

	/**
	 * Chiede una stringa
	 * 
	 * @return la stringa inserita
	 */
	public String leggiStringa() {
		String risposta = in.nextLine();
		return risposta.trim();
	}

	/**
	 * Chiede un intero, effettua la richiesta finche' la stringa inserita e'
	 * corretta
	 * 
	 * @return un intero
	 */
	public int leggiIntero() {
		Integer num = null;
		while (num == null) {
			try {
				String str = leggiStringa();
				str = str.trim();
				num = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				out.println("Devi inserire un intero");
			}
		}
		return num;
	}

	/**
	 * Chiede un intero che deve essere compreso fra min e max, i limiti sono
	 * inclusi.
	 * 
	 * @param min
	 *            il numero minimo
	 * @param max
	 *            il numero massimo
	 * @return un intero compreso in due limiti
	 */
	public int leggiIntero(int min, int max) {
		Integer num = leggiIntero();
		while (num < min || num > max) {
			out.println("Devi inserire un intero compreso fra " + min + " e " + max);
			num = leggiIntero();
		}
		return num;
	}

	/**
	 * Chiede un intero che deve appartenere a un inseme di numeri
	 * 
	 * @param insieme
	 *            l'insieme di numeri di cui l'intero deve far parte
	 * @return il numero scelto
	 */
	public int leggiIntero(Collection<Integer> insieme) {
		Integer num = leggiIntero();
		while (!insieme.contains(num)) {
			out.println("Devi inserire un numero presente nell'elenco");
			num = leggiIntero();
		}
		return num;
	}

	/**
	 * Chiede all'utente un intero che corrisponde alla posizione di un elemento
	 * in una collezione
	 * 
	 * @param coll
	 *            la collezione fra cui scegliere l'elemento
	 * @return un elemento di una collezione
	 */
	public <E> E scegliElemento(Collection<E> coll) {
		int scelta = leggiIntero(1, coll.size()) - 1;
		int indice = 0;
		for (E elem : coll) {
			if (indice == scelta) {
				return elem;
			}
			indice++;
		}

		throw new IllegalArgumentException("Impossibile scegliere dalla lista");
	}

}
