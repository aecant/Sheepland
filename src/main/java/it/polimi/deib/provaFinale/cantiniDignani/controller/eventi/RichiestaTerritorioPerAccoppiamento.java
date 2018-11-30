package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

import java.util.List;

public class RichiestaTerritorioPerAccoppiamento extends Richiesta {
	private static final long serialVersionUID = -8600833405453222261L;

	private List<Integer> terrDisponibili;

	public RichiestaTerritorioPerAccoppiamento(List<Integer> terrDisp) {
		this.terrDisponibili = terrDisp;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaTerritorioPerAccoppiamento(terrDisponibili);
	}

}
