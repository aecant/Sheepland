package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaPastore extends Richiesta {

	private static final long serialVersionUID = -175401506291334931L;

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPastore();
	}

}
