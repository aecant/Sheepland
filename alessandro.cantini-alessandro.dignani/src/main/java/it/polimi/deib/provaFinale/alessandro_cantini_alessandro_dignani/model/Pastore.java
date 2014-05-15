package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Pastore extends PedinaSuStrada {
    private Colore colore;

    public void muoviIn(Strada posizione) {
    	super.setStrada(posizione);
    }

    public Colore getColore() {
        return this.colore;
    }

    public void setColore(Colore value) {
        this.colore = value;
    }

    private enum Colore {
        ROSSO,
        BLU,
        VERDE,
        GIALLO;
    }
}
