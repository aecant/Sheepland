package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;

public class Abbattimento extends Mossa {

	private static final long serialVersionUID = 1L;
	private TipoOvino TipoOvino;

	public Abbattimento(Giocatore giocatore, TipoOvino TipoOvino) {
		super(giocatore);
		this.TipoOvino = TipoOvino;
	}
	
	public TipoOvino getTipoOvino() {
		return TipoOvino;
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		Client.getUI().abbattimento(super.getGiocatore(), TipoOvino);
	}
	
	
}
