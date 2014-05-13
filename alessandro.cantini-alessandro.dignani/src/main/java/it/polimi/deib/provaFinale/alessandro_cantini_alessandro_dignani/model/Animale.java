package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Animale {
	private Territorio posizione;

	public Animale (Territorio posizione){
		this.posizione = posizione;
	}
	
	public void muoviIn(Territorio territorio) {
		posizione = territorio;
	}

	public Territorio getPosizione() {
		return posizione;
	}

}
