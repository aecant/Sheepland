package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class PosizionamentoPastore extends Mossa {
	private static final long serialVersionUID = 663578033332385725L;
	
	private int strada;

	public PosizionamentoPastore(String giocatore, int strada) {
		super(giocatore);
		this.strada = strada;
	}

	public int getStrada() {
		return strada;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaGiocatori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().selezionePosizioneInizialePastore(super.getGiocatore(), strada);
	}

	@Override
	public String toString() {
		return  getGiocatore() + " ha posizionato il pastore sulla strada " + strada;
	}

}
