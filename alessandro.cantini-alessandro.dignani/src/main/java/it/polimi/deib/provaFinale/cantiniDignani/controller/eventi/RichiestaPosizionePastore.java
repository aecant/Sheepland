package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaPosizionePastore extends Richiesta {

	private static final long serialVersionUID = 708070364097104922L;
	
	private int[] stradeLibere;

	public RichiestaPosizionePastore(int[] stradeLibere) {
		this.stradeLibere = stradeLibere;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().sceltaPosizionePastore(stradeLibere);
	}

}
