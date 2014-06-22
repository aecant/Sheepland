package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.util.List;

public class MarketRichiestaTesseraDaVendere extends Richiesta {

	private static final long serialVersionUID = 5108713140234150459L;

	private final List<TipoTerritorio> terrDisp;
	
	public MarketRichiestaTesseraDaVendere(List<TipoTerritorio> terrDisp) {
		this.terrDisp = terrDisp;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().marketRichiestaTesseraDaVendere(terrDisp);
	}


}
