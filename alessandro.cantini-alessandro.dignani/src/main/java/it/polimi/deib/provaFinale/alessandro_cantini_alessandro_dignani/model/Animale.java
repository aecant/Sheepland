package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Animale {
	private Territorio posizione;

	public Animale(Territorio posizione) {
		this.posizione = posizione;
	}

	public void muoviIn(Territorio territorio) {
		posizione = territorio;
	}

	
	/**
	 * Data una lista di animali e un territorio, controlla se c'è un animale all'interno
	 * della lista che occupa quel territorio
	 * 
	 * @param lista
	 * @param territorio
	 * @return
	 */
	public static boolean territorioOccupato(ArrayList<Animale> lista, Territorio territorio) {
		for(Animale a : lista)
			if(a.getPosizione().equals(territorio))
				return true;
		return false;
	}

	public Territorio getPosizione() {
		return posizione;
	}

}
