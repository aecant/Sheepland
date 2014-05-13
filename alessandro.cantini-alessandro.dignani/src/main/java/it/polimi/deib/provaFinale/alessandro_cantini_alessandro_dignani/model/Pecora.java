package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Pecora extends Animale {

	private boolean maschio;

	public Pecora(Territorio posizione, boolean maschio) {
		super(posizione);
		this.maschio = maschio;
	}

	public boolean isMaschio() {
		return this.maschio;
	}

}
