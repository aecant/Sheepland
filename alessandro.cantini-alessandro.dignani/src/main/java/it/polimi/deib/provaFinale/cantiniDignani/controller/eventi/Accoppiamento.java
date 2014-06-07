package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class Accoppiamento extends Mossa {
	private static final long serialVersionUID = -4505056058824206511L;

	private int territorio;
	private boolean aBuonFine;

	public Accoppiamento(String giocatore, int territorio) {
		super(giocatore);
		this.territorio = territorio;
	}

	public Accoppiamento(Accoppiamento acc, boolean aBuonFine) {
		this(acc.getGiocatore(), acc.getTerritorio());
		this.aBuonFine = aBuonFine;
	}

	@Override
	public void aggiornaDati() {
		if (aBuonFine) {
			ClientMain.getDatiPartita().aggiornaTerritori();
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
