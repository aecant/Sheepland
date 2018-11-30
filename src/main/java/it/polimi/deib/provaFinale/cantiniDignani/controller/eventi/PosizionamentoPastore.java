package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

public class PosizionamentoPastore extends Mossa {
	private static final long serialVersionUID = 663578033332385725L;

	private int strada;
	private Giocatore[] giocDaAggiornare;

	public PosizionamentoPastore(String giocatore, int strada, Giocatore[] giocDaAggiornare) {
		super(giocatore);
		this.strada = strada;
		this.giocDaAggiornare = giocDaAggiornare.clone();
	}

	@Override
	public void aggiornaDati() {
		MainClient.getDatiPartita().setGiocatori(giocDaAggiornare);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().selezionePosizioneInizialePastore(giocatore, strada);
	}

	@Override
	public String toString() {
		return "Posizionamento Pastore sulla strada " + strada + " da parte di " + giocatore;
	}
}
