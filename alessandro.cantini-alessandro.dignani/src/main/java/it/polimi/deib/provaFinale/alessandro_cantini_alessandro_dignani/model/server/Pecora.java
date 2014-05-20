package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

public class Pecora extends Animale {

	private boolean maschio;

	public Pecora(Territorio posizione, boolean maschio) {
		super(posizione);
		this.maschio = maschio;
	}

	public boolean isMaschio() {
		return this.maschio;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Pecora))
			return false;
		Pecora other = (Pecora) obj;
		if (maschio != other.maschio)
			return false;
		return true;
	}

}
