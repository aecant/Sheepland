package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public class Abbattimento extends Mossa {
	private static final long serialVersionUID = 4611021483668316628L;

	private TipoAnimale tipoOvino;
	private int territorio;
	private boolean aBuonFine;
	private DatiTerritorio[] terrDaAggiornare;
	private Giocatore[] giocDaAggiornare;

	public Abbattimento(String giocatore, TipoAnimale tipoOvino, int territorio, boolean aBuonFine, DatiTerritorio[] terrDaAggiornare, Giocatore[] giocDaAggiornare) {
		super(giocatore);
		this.tipoOvino = tipoOvino;
		this.territorio = territorio;
		this.aBuonFine = aBuonFine;
		this.terrDaAggiornare = terrDaAggiornare;
		this.giocDaAggiornare = giocDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		if (aBuonFine) {
			ClientMain.getDatiPartita().setTerritori(terrDaAggiornare);
			ClientMain.getDatiPartita().setGiocatori(giocDaAggiornare);
		}
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().abbattimento(giocatore, tipoOvino, territorio, aBuonFine);
	}

}
