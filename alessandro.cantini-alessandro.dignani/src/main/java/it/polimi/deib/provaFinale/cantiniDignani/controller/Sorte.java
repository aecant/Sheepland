package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;

import java.util.Random;

public class Sorte {
	/**
	 * Costruttore privato per nascondere quello pubblico di default
	 */
	private Sorte() {
	}

	/**
	 * Restituisce un numero casuale fra 1 e 6 compresi
	 * 
	 * @return un numero casuale fra 1 e 6 compresi
	 */
	public static int lanciaDado() {
		return numeroCasuale(1, 6);
	}

	/**
	 * Restituisce casualmente true o false
	 * 
	 * @return true o false casualmente
	 */
	public static boolean lanciaMoneta() {
		return new Random().nextBoolean();
		// return numeroCasuale(0, 1) == 0 ? false : true;
	}

	/**
	 * Restituisce un ovino che e' randomicamente una pecora, un montone o un
	 * agnello. Nel caso in cui sia agnello il sesso viene stabilito lanciando
	 * una moneta.
	 * 
	 * @param t
	 *            il territorrio su cui creare l'ovino
	 * @return una pecora creata randomicamente
	 */
	public static Pecora pecoraRandom(Territorio t) {
		switch (numeroCasuale(1, 3)) {
		case 1:
			return new Pecora(t, true);
		case 2:
			return new Pecora(t, false);
		default:
			return agnelloRandom(t);
		}

	}

	/**
	 * Restituisce un agnello che diventera' randomicamente pecora o montone
	 * 
	 * @param t
	 *            il territorio su cui creare l'agnello
	 * @return un agnello di sesso casuale
	 */
	public static Agnello agnelloRandom(Territorio t) {
		return new Agnello(t, lanciaMoneta());
	}

	/**
	 * Restituisce un numero casuale compreso fra i due parametri, parametri
	 * compresi
	 * 
	 * @param min
	 *            il valore minimo
	 * @param max
	 *            il valore massimo
	 * @return un numero casuale compreso fra
	 * @throws IllegalArgumentException
	 *             se min e' maggiore o uguale a max
	 */
	public static int numeroCasuale(int min, int max) throws IllegalArgumentException {
		if (min >= max) {
			throw new IllegalArgumentException("il primo parametro deve essere minore del secondo");
		}

		return new Random().nextInt(max - min + 1) + min;
	}

}
