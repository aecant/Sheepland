package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

public class Abbattimento extends Mossa {

	private static final long serialVersionUID = 1L;
	private TipoOvino TipoOvino;

	public Abbattimento(String giocatore, TipoOvino TipoOvino) {
		super(giocatore);
		this.TipoOvino = TipoOvino;
	}
	
	public TipoOvino getTipoOvino() {
		return TipoOvino;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().abbattimento(super.getGiocatore(), TipoOvino);
	}
	
	
}
