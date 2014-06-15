package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

public class PosizionamentoPastore extends Mossa {
	private static final long serialVersionUID = 663578033332385725L;

	private int strada;
	private Giocatore[] giocDaAggiornare;

	public PosizionamentoPastore(String giocatore, int strada, Giocatore[] giocDaAggiornare) {
		super(giocatore);
		this.strada = strada;
	}

	public int getStrada() {
		return strada;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setGiocatori(giocDaAggiornare);
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().selezionePosizioneInizialePastore(super.getGiocatore(), strada);
	}

	@Override
	public String toString() {
		return getGiocatore() + " ha posizionato il pastore sulla strada " + strada;
	}

}
