package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class PedinaSuStrada implements Serializable {

	private static final long serialVersionUID = -6921466505175740285L;
	
	private Strada strada;

	public PedinaSuStrada(Strada strada) {
		this.strada = strada;
	}

	public Strada getStrada() {
		return this.strada;
	}

	protected void setStrada(Strada strada) {
		this.strada = strada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((strada == null) ? 0 : strada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PedinaSuStrada)) {
			return false;
		}
		PedinaSuStrada other = (PedinaSuStrada) obj;
		if (strada == null) {
			if (other.strada != null) {
				return false;
			}
		} else if (!strada.equals(other.strada)) {
			return false;
		}
		return true;
	}

}
