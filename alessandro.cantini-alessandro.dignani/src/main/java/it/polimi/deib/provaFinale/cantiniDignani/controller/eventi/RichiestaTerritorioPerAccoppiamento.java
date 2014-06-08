package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaTerritorioPerAccoppiamento extends Richiesta {

	private static final long serialVersionUID = -8600833405453222261L;

	List<Integer> terrDisponibili;

	public RichiestaTerritorioPerAccoppiamento(List<Integer> terrDisp) {
		this.terrDisponibili = terrDisp;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTerritorioPerAccoppiamento(terrDisponibili);
	}

}
