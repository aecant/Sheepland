package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class Tessera implements Serializable {

	private static final long serialVersionUID = -4405168162325304567L;

	private final TipoTerritorio tipo;
	private final int costo;

	public Tessera(TipoTerritorio tipo, int costo) {
		this.costo = costo;
		this.tipo = tipo;
	}

	public int getCosto() {
		return this.costo;
	}

	public TipoTerritorio getTipo() {
		return this.tipo;
	}

	@Override
	public String toString() {
		return tipo + ":" + costo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + costo;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (!(obj instanceof Tessera)) {
			return false;
		}
		Tessera other = (Tessera) obj;
		if (costo != other.costo) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		return true;
	}

}
