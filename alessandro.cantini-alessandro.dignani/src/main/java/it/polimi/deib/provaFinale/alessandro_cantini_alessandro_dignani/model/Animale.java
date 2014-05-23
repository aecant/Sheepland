package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Animale {
	private Territorio posizione;

	public Animale(Territorio posizione) {
		this.posizione = posizione;
	}

	public void muoviIn(Territorio territorio) {
		posizione = territorio;
	}

	public Territorio getPosizione() {
		return posizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posizione == null) ? 0 : posizione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Animale))
			return false;
		Animale other = (Animale) obj;
		if (posizione == null) {
			if (other.posizione != null)
				return false;
		} else if (!posizione.equals(other.posizione))
			return false;
		return true;
	}

}
