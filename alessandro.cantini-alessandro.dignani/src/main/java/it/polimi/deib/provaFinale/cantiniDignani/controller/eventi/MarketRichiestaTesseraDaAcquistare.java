package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;

import java.util.List;

public class MarketRichiestaTesseraDaAcquistare extends Richiesta {

	private static final long serialVersionUID = 2351183950816460794L;
	
	private final List<TesseraInVendita> tessereDisponibili;

	public MarketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		this.tessereDisponibili = tessereDisponibili;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().marketRichiestaTesseraDaAcquistare(tessereDisponibili);
	}
}
