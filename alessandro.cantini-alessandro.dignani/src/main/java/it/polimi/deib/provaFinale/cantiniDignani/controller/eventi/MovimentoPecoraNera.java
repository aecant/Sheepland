package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;

public class MovimentoPecoraNera extends MovimentoAnimale {

	private static final long serialVersionUID = -742787287192836056L;

	public MovimentoPecoraNera(Territorio origine, Territorio destinazione) {
		super(origine, destinazione);
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoPecoraNera(super.getOrigine(), super.getDestinazione());
	}

}
