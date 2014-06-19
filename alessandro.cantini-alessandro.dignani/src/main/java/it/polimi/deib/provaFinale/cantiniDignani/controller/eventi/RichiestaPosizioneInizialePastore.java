package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class RichiestaPosizioneInizialePastore extends Richiesta {

	private static final long serialVersionUID = 708070364097104922L;
	
	private boolean[] stradeLibere;

	public RichiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		this.stradeLibere = stradeLibere.clone();
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaPosizioneInizialePastore(stradeLibere);
	}

	@Override
	public String toString() {
		return "richiesta posizione iniziale pastore";
	}
	
}
