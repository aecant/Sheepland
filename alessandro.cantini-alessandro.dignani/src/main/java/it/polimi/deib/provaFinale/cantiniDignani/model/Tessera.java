package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class Tessera implements Serializable{
	
	private static final long serialVersionUID = -4405168162325304567L;
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
