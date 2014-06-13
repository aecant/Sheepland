package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.util.ArrayList;
import java.util.Collection;

public class RichiestaTesseraDaAcquistare extends Richiesta {

	private static final long serialVersionUID = 2560438399810730711L;

	private ArrayList<Tessera> tessereDisp;
		
	public RichiestaTesseraDaAcquistare(Collection<Tessera> tessereDisp) {
		this.tessereDisp = (ArrayList<Tessera>) tessereDisp;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTesseraDaAcquistare(tessereDisp);
	}

}
