package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Evento;

public class LancioDado extends Evento {

	private static final long serialVersionUID = -7516514552097599699L;
	private Integer numero;
	
	public LancioDado(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

}
