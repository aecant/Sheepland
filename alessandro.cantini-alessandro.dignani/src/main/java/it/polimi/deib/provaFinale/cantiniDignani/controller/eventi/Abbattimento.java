package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public class Abbattimento extends Mossa {
	private static final long serialVersionUID = 4611021483668316628L;

	private TipoAnimale tipoOvino;
	private int territorio;
	private boolean aBuonFine;

	public Abbattimento(String giocatore, TipoAnimale tipoOvino, int territorio, boolean aBuonFine) {
		super(giocatore);
		this.tipoOvino = tipoOvino;
		this.territorio = territorio;
		this.aBuonFine = aBuonFine;
	}

	public TipoAnimale getTipoOvino() {
		return tipoOvino;
	}

	public int getTerritorio() {
		return territorio;
	}

	public boolean isaBuonFine() {
		return aBuonFine;
	}

	@Override
	public void aggiornaDati() {
		if (aBuonFine) {
			ClientMain.getDatiPartita().aggiornaTerritori();
			ClientMain.getDatiPartita().aggiornaGiocatori();
		}
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().abbattimento(super.getGiocatore(), tipoOvino, territorio, aBuonFine);
	}

}
