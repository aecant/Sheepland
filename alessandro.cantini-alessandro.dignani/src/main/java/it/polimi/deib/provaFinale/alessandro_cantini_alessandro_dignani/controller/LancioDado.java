package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;


public class LancioDado implements Evento {

	private static final long serialVersionUID = -7516514552097599699L;
	private Integer numero;
	
	public LancioDado(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void aggiornaDati() {
		;
	}

	public void visualizza() {
		Client.getUI().lancioDado(numero);
	}

}
