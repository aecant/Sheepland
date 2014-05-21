package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

public class AcquistoTessera extends Mossa {
	
	private static final long serialVersionUID = -8771805864958018539L;
	Tessera tessera;

	public AcquistoTessera(Giocatore giocatore, Tessera tessera) {
		super(giocatore);
		this.tessera = tessera;
	}

	public Tessera getTessera() {
		return tessera;
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().aggiornaTessereInCima();
	}

	@Override
	public void visualizza() {
		Client.getUI().acquistoTessera(super.getGiocatore(), getTessera());
	}

}
