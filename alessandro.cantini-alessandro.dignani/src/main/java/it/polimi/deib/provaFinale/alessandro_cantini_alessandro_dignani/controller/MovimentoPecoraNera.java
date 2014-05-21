package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;

public class MovimentoPecoraNera extends MovimentoAnimale {

	private static final long serialVersionUID = -742787287192836056L;

	public MovimentoPecoraNera(Territorio origine, Territorio destinazione) {
		super(origine, destinazione);
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		Client.getUI().movimentoPecoraNera(super.getOrigine(), super.getDestinazione());
	}

}
