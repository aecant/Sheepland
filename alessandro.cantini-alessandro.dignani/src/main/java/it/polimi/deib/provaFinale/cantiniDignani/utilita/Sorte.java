package it.polimi.deib.provaFinale.cantiniDignani.utilita;

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
		if (min > max) {
			throw new IllegalArgumentException("il primo parametro deve essere minore o uguale al secondo");
		}

		return new Random().nextInt(max - min + 1) + min;
	}

}
