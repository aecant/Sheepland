package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Pastore extends PedinaSuStrada {

	private static final long serialVersionUID = -7195381118983518612L;
	private ColorePastore colore;

	public Pastore(Strada strada, ColorePastore colore) {
		super(strada);
		this.colore = colore;
	}

	public void muoviIn(Strada posizione) {
		if(posizione == null) {
			throw new NullPointerException();
		}
		super.setStrada(posizione);
	}

	public ColorePastore getColore() {
		return this.colore;
	}

	@Override
	public String toString() {
		return "pastore " + colore + " in " + super.getStrada();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((colore == null) ? 0 : colore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Pastore)) {
			return false;
		}
		Pastore other = (Pastore) obj;
		if (colore != other.colore) {
			return false;
		}
		return true;
	}

}
