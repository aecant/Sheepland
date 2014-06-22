package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

public class MarketRichiestaPrezzo extends Richiesta{

	private static final long serialVersionUID = 9213617610540121422L;

	private final TipoTerritorio tipo;

	public MarketRichiestaPrezzo(TipoTerritorio tipo) {
		this.tipo = tipo;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().marketRichiestaPrezzo(tipo);
	}
	

}
