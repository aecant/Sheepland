package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Rappresenta l'insieme delle tessere. E' composto da una pila di carte per
 * ogni tipo di territorio presente in {@link TipoTerritorio}, eccetto
 * Sheepsburg.
 * 
 */
public class Mazzo {
	private HashMap<TipoTerritorio, Stack<Tessera>> mazzo;

	/**
	 * Crea uno stack di carte per ogni tipo contenuto in TipoTerritorio,
	 * eccetto Sheepsburg, con il valore più basso in cima alla pila
	 */
	public Mazzo() {
		mazzo = new HashMap<TipoTerritorio, Stack<Tessera>>();
		for (TipoTerritorio t : TipoTerritorio.values())
			if (t != TipoTerritorio.SHEEPSBURG) {
				Stack<Tessera> pilaDiCarte = new Stack<Tessera>();
				for (int i = Costanti.MAX_VALORE_TESSERA; i >= 0; i--)
					pilaDiCarte.push(new Tessera(t, i));
				mazzo.put(t, pilaDiCarte);
			}
	}

	/**
	 * Legge la tessera in cima alla pila del tipo indicato come parametro
	 * 
	 * @param tipo
	 * @return La tessera di tipo richiesto
	 * @throws MazzoFinitoException
	 *             nel caso il mazzo sia finito
	 * @throws IllegalArgumentException
	 *             nel caso si cerchi di prelevare la tessera Sheepsburg
	 */
	public Tessera leggiTesseraInCima(TipoTerritorio tipo) throws MazzoFinitoException, IllegalArgumentException {
		if (tipo == TipoTerritorio.SHEEPSBURG)
			throw new IllegalArgumentException("Non esistono tessere di tipo Sheepsburg");

		try {
			return mazzo.get(tipo).peek();
		} catch (EmptyStackException e) {
			throw new MazzoFinitoException(e);
		}
	}

	/**
	 * Legge la tessera in cima al mazzo usando {@link Mazzo#leggiTesseraInCima},
	 * se non ci sono eccezioni la scarta
	 * 
	 * @param tipo
	 *            il tipo della carta che si vuole prelevare
	 * @return la tessera del tipo richiesto
	 * @throws MazzoFinitoException
	 *             nel caso il mazzo sia finito
	 * @throws IllegalArgumentException
	 *             nel caso si cerchi di prelevare la tessera Sheepsburg
	 */
	public Tessera prelevaTessera(TipoTerritorio tipo) throws MazzoFinitoException, IllegalArgumentException {
		Tessera t = leggiTesseraInCima(tipo);
		mazzo.get(tipo).pop();
		return t;
	}

	/**
	 * Restituisce il numero di carte rimaste di un certo tipo di territorio
	 * 
	 * @param tipo
	 * @return il numero di carte rimaste di un certo tipo di territorio
	 */
	public Integer getTessereRimaste(TipoTerritorio tipo) {
		return mazzo.get(tipo).size();
	}

	/**
	 * Eccezione che indica che la pila di tessere di un territorio e' finita
	 */
	public class MazzoFinitoException extends RuntimeException {

		private static final long serialVersionUID = -797454772948488201L;

		public MazzoFinitoException(String message, Throwable cause) {
			super(message, cause);
		}

		public MazzoFinitoException(Throwable cause) {
			super(cause);
		}

		public MazzoFinitoException() {
			super();
		}

		public MazzoFinitoException(String s) {
			super(s);
		}
	}

	@Override
	public String toString() {
		String s = "Mazzo:\n";
		for (TipoTerritorio terr : mazzo.keySet()) {
			s += terr + ": " + mazzo.get(terr) + "\n";
		}
		return s;
	}

}
