package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class Strada implements Serializable {
	private static final long serialVersionUID = 6178303418989298078L;

	private Territorio territorio1;
	private Territorio territorio2;
	private Integer codice;

	public Strada(Territorio territorio1, Territorio territorio2, Integer codice) {
		if (territorio1 == null || territorio2 == null) {
			throw new IllegalArgumentException();
		}
		if (territorio1.equals(territorio2)) {
			throw new IllegalArgumentException("I due territori devono essere diversi");
		}
		this.territorio1 = territorio1;
		this.territorio2 = territorio2;
		this.codice = codice;
	}

	public Strada(Territorio territorio1, Territorio territorio2) {
		this(territorio1, territorio2, null);
	}

	public Territorio getTerritorio1() {
		return this.territorio1;
	}

	public Territorio getTerritorio2() {
		return this.territorio2;
	}

	public Integer getCodice() {
		return this.codice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((territorio1 == null) ? 0 : territorio1.hashCode());
		result = prime * result + ((territorio2 == null) ? 0 : territorio2.hashCode());
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
		if (!(obj instanceof Strada)) {
			return false;
		}
		Strada other = (Strada) obj;
		if ((territorio1.equals(other.territorio1) && territorio2.equals(other.territorio2)) || territorio1.equals(other.territorio2) && territorio2.equals(other.territorio1)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		if (this.equals(Mappa.STRADA_INESISTENTE)) {
			return "Strada inesistente";
		}
		return "Strada " + codice;
	}

}
