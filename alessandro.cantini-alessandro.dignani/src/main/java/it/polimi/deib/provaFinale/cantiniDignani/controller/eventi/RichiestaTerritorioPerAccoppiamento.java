package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.util.Collection;

public class RichiestaTerritorioPerAccoppiamento extends Richiesta {
	private static final long serialVersionUID = -8600833405453222261L;

	private Collection<Integer> terrDisponibili;

	public RichiestaTerritorioPerAccoppiamento(Collection<Integer> terrDisp) {
		this.terrDisponibili = Utilita.rendiSerializzabile(terrDisp);
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTerritorioPerAccoppiamento(terrDisponibili);
	}

}
