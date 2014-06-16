package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.io.Serializable;

/**
 * Rappresenta una coppia di due elementi generici
 * 
 * @param <P>
 *            il tipo del primo elemento
 * @param <S>
 *            il tipo del secondo elemento
 */
public class Coppia<P extends Serializable, S extends Serializable> implements Serializable {
	private static final long serialVersionUID = 2945128168506394320L;

	public final P primo;
	public final S secondo;

	public Coppia(P s, S d) {
		this.primo = s;
		this.secondo = d;
	}

	/**
	 * Metodo statico alternativo al costruttore standard
	 * 
	 * @param primo
	 *            il primo membro della coppia
	 * @param secondo
	 *            il secondo membro della coppia
	 * @return una coppia composta dagli elementi passati come parametro
	 */
	public static <E extends Serializable, F extends Serializable> Coppia<E, F> creaCoppia(E primo, F secondo) {
		return new Coppia<E, F>(primo, secondo);
	}

	@Override
	public String toString() {
		return "Coppia: " + primo + ", " + secondo;
	}

}
