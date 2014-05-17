package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.List;

public class Animale {
	private Territorio posizione;

	public Animale(Territorio posizione) {
		this.posizione = posizione;
	}

	public void muoviIn(Territorio territorio) {
		posizione = territorio;
	}

	
	/**
	 * Data una strada ed una lista di pedine su strada controlla se la strada è occupata da una di quelle pedine
	 * 
	 * @param lista
	 * @param territorio
	 * @return
	 */
	public static boolean territorioOccupato(List<Animale> lista, Territorio territorio) {
		for(Animale a : lista)
			if(a.getPosizione().equals(territorio))
				return true;
		return false;
	}

	public Territorio getPosizione() {
		return posizione;
	}

}
