package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class SelezionePosizioneInizialePastore extends Mossa {

	private static final long serialVersionUID = 663578033332385725L;
	private int strada;

	public SelezionePosizioneInizialePastore(String giocatore, int strada) {
		super(giocatore);
		this.strada = strada;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getConnessione().chiediPastori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().selezionePosizioneInizialePastore(super.getGiocatore(), strada);
	}
}
