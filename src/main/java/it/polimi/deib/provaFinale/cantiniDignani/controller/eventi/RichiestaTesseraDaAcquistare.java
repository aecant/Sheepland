package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.util.List;

public class RichiestaTesseraDaAcquistare extends Richiesta {

	private static final long serialVersionUID = 2560438399810730711L;

	private List<Tessera> tessereDisp;
		
	public RichiestaTesseraDaAcquistare(List<Tessera> tessereDisp) {
		this.tessereDisp =tessereDisp;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaTesseraDaAcquistare(tessereDisp);
	}

}
