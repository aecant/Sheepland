package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Agnello extends Pecora {

	public Agnello(Territorio posizione, boolean maschio) {
		super(posizione, maschio);
		eta = 0;
	}

	private int eta;

	public void invecchia() {
		eta++;
	}

	public int getEta() {
		return eta;
	}

}
