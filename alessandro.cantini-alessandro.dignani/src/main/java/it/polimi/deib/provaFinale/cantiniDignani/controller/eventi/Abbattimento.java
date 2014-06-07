package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

public class Abbattimento extends Mossa {
	private static final long serialVersionUID = 4611021483668316628L;

	private TipoOvino tipoOvino;
	private int territorio;

	public Abbattimento(String giocatore, TipoOvino tipoOvino, int territorio) {
		super(giocatore);
		this.tipoOvino = tipoOvino;
		this.territorio = territorio;
	}

	public TipoOvino getTipoOvino() {
		return tipoOvino;
	}

	public int getTerritorio() {
		return territorio;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().abbattimento(super.getGiocatore(), tipoOvino, territorio);
	}

}
