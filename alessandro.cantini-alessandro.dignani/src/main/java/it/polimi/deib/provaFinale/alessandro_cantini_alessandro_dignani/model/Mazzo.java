package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Mazzo {
	private HashMap<TipoTerritorio, Stack<Tessera>> mazzo;

	// TODO migliorare chiarezza
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

	
	public Tessera leggiTesseraInCima(TipoTerritorio tipo) {
		if (tipo == TipoTerritorio.SHEEPSBURG)
			throw new IllegalArgumentException("Non esistono tessere di tipo Sheepsburg");
		
		try {
			return mazzo.get(tipo).peek();
		}
		catch (EmptyStackException e) {
			throw new MazzoFinitoException();
		}
	}
	
	public Tessera prelevaTessera(TipoTerritorio tipo) throws MazzoFinitoException, IllegalArgumentException {
		Tessera t = leggiTesseraInCima(tipo);
		mazzo.get(tipo).pop();
		return t;
	}
	
	public Integer getTessereRimaste(TipoTerritorio tipo) {
		return mazzo.get(tipo).size();
	}
	
	public class MazzoFinitoException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public MazzoFinitoException() {
			super();
		}

		public MazzoFinitoException(String s) {
			super(s);
		}
	}

	
	public String toString() {
		String s = "Mazzo:\n";
		for (TipoTerritorio terr : mazzo.keySet()) {
			s += terr + ": " + mazzo.get(terr)+"\n";
		}
		return s;
	}

}
