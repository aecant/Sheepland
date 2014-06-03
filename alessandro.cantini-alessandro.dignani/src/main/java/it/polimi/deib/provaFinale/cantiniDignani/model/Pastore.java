package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Pastore extends PedinaSuStrada {

	private ColorePastore colore;

	public Pastore(Strada strada, ColorePastore colore) {
		super(strada);
		this.colore = colore;
	}

	public void muoviIn(Strada posizione) {
		super.setStrada(posizione);
	}

	public ColorePastore getColore() {
		return this.colore;
	}
	
	
}
