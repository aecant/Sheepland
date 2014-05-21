package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;

public class MovimentoLupo extends MovimentoAnimale {

	private static final long serialVersionUID = 1L;

	public MovimentoLupo(Territorio origine, Territorio destinazione) {
		super(origine, destinazione);
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		Client.getUI().movimentoLupo(super.getOrigine(), super.getDestinazione());
	}

}
