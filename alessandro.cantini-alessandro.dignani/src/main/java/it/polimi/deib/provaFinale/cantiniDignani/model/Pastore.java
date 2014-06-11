package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Pastore extends PedinaSuStrada {

	private static final long serialVersionUID = -7195381118983518612L;
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
	
	@Override
	public String toString() {
		return "pastore " + colore + " in " + super.getStrada();
	}
	
}
