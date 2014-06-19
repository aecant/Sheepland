package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class RichiestaPastore extends Richiesta {

	private static final long serialVersionUID = -175401506291334931L;

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaPastore();
	}

}
