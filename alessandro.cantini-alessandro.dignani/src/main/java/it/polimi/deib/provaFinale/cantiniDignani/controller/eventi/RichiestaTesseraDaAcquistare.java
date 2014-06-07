package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.util.Collection;

public class RichiestaTesseraDaAcquistare extends Richiesta {

	private static final long serialVersionUID = 2560438399810730711L;

	private Collection<Tessera> tessereDisp;
		
	public RichiestaTesseraDaAcquistare(Collection<Tessera> tessereDisp) {
		this.tessereDisp = tessereDisp;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTesseraDaAcquistare(tessereDisp);
	}

}
