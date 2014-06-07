package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaPosizioneInizialePastore extends Richiesta {

	private static final long serialVersionUID = 708070364097104922L;
	
	private boolean[] stradeLibere;

	public RichiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		this.stradeLibere = stradeLibere;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPosizioneInizialePastore(stradeLibere);
	}

}
