package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Tessera {
	private TipoTerritorio tipo;
	private int costo;


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
}