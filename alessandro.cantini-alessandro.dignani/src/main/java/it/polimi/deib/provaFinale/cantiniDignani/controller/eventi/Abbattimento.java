package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public class Abbattimento extends Mossa {
	private static final long serialVersionUID = 4611021483668316628L;

	private TipoAnimale tipoOvino;
	private int territorio;
	private boolean aBuonFine;
	private DatiTerritorio[] terrDaAggiornare;

	public Abbattimento(String giocatore, TipoAnimale tipoOvino, int territorio, boolean aBuonFine, DatiTerritorio[] terrDaAggiornare) {
		super(giocatore);
		this.tipoOvino = tipoOvino;
		this.territorio = territorio;
		this.aBuonFine = aBuonFine;
		this.terrDaAggiornare = terrDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		if (aBuonFine) {
			MainClient.getDatiPartita().setTerritori(terrDaAggiornare);
		}
	}

	@Override
	public void visualizza() {
		MainClient.getUI().abbattimento(giocatore, tipoOvino, territorio, aBuonFine);
	}

}
