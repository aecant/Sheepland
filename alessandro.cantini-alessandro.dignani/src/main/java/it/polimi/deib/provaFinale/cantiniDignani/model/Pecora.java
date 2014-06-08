package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Pecora extends Animale {

	private boolean maschio;

	public Pecora(Territorio posizione, boolean maschio) {
		super(posizione);
		this.maschio = maschio;
	}

	public boolean isMaschio() {
		return this.maschio;
	}

	public TipoAnimale getTipoOvino() {
		if (maschio) {
			return TipoAnimale.MONTONE;
		} else {
			return TipoAnimale.PECORA;
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (maschio ? 1231 : 1237);
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
		if (!(obj instanceof Pecora)) {
			return false;
		}
		Pecora other = (Pecora) obj;
		if (maschio != other.maschio) {
			return false;
		}
		return true;
	}

}
