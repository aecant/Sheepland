package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;

public class LancioDado implements Evento {
	private static final long serialVersionUID = -7516514552097599699L;

	private Integer numero;
	private MotivoLancioDado motivo;

	public LancioDado(Integer numero, MotivoLancioDado motivo) {
		if (numero < 1 && numero > 6) {
			throw new IllegalArgumentException("Il numero del dado dev'essere compreso fra 1 e 6");
		}
		this.numero = numero;
		this.motivo = motivo;
	}

	public void aggiornaDati() {
		;
	}

	public void visualizza() {
		MainClient.getUI().lancioDado(numero, motivo);
	}

	@Override
	public String toString() {
		return "Dado lanciato: " + numero + " per " + motivo;
	}

}
