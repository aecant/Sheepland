package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class Strada implements Serializable {
	private static final long serialVersionUID = 6178303418989298078L;

	private final Territorio territorio1;
	private final Territorio territorio2;
	private final Integer codice;

	/**
	 * Crea un'istanza di strada a partire da due territori e il codice univoco
	 * 
	 * @param territorio1
	 *            il primo territorio
	 * @param territorio2
	 *            il secondo territorio
	 * @param codice
	 *            il codice della strada
	 */
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

	/**
	 * Crea un'istanza di strada a partire da due territori
	 * 
	 * @param territorio1
	 *            il primo territorio
	 * @param territorio2
	 *            il secondo territorio
	 */
	public Strada(Territorio territorio1, Territorio territorio2) {
		this(territorio1, territorio2, null);
	}

	/**
	 * Restituisce il primo territorio della strada
	 * 
	 * @return il primo territorio della strada
	 */
	public Territorio getTerritorio1() {
		return this.territorio1;
	}

	/**
	 * Restituisce il secondo territorio della strada
	 * 
	 * @return il secondo territorio della strada
	 */
	public Territorio getTerritorio2() {
		return this.territorio2;
	}

	/**
	 * Restituisce il codice univoco della strada
	 * 
	 * @return il codic della strada
	 */
	public Integer getCodice() {
		return this.codice;
	}

	/**
	 * Restituisce l'hash code della strada
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((territorio1 == null) ? 0 : territorio1.hashCode());
		result = prime * result + ((territorio2 == null) ? 0 : territorio2.hashCode());
		return result;
	}

	/**
	 * Compara i due territori della strada. I territori sono interscambiabili.
	 */
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

	/**
	 * Restituisce una rappresentazione della strada come stringa
	 */
	@Override
	public String toString() {
		if (this.equals(Mappa.STRADA_INESISTENTE)) {
			return "Strada inesistente";
		}
		return "Strada " + codice;
	}

}
