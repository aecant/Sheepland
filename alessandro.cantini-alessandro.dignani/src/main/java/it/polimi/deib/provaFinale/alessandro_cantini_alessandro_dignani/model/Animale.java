package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public abstract class Animale {
    private Territorio posizione;

    public void muoviIn(Territorio territorio) {
    	posizione = territorio;
    }


	public Territorio getPosizione() {
		return posizione;
	}

    public abstract Territorio scegliMovimento();

    
}
