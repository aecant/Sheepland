package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

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
		ClientMain.getUI().lancioDado(numero);
	}

	@Override
	public String toString() {
		return "Dado lanciato: " + numero;
	}

}
