package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

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
