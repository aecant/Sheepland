package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

public class CompraTessera extends Mossa {
	
	private static final long serialVersionUID = -8771805864958018539L;
	Tessera tessera;

	public CompraTessera(Giocatore giocatore, Tessera tessera) {
		super(giocatore);
		this.tessera = tessera;
	}

	public Tessera getTessera() {
		return tessera;
	}

}
