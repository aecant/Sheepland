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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + eta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Agnello))
			return false;
		Agnello other = (Agnello) obj;
		if (eta != other.eta)
			return false;
		return true;
	}

}
