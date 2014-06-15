package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;

public class Accoppiamento extends Mossa {
	private static final long serialVersionUID = -4505056058824206511L;

	private int territorio;
	private boolean aBuonFine;
	private DatiTerritorio[] terrDaAggiornare;

	public Accoppiamento(String giocatore, int territorio, boolean aBuonFine, DatiTerritorio[] terrDaAggiornare) {
		super(giocatore);
		this.territorio = territorio;
		this.aBuonFine = aBuonFine;
		this.terrDaAggiornare = terrDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		if (aBuonFine) {
			ClientMain.getDatiPartita().setTerritori(terrDaAggiornare);
		}
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().accoppiamento(super.getGiocatore(), territorio, aBuonFine);
	}

	public int getTerritorio() {
		return territorio;
	}

	public boolean isaBuonFine() {
		return aBuonFine;
	}

}
