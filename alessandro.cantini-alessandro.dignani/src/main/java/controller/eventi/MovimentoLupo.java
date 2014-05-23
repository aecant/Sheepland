package controller.eventi;

import model.Territorio;
import controller.ClientMain;

public class MovimentoLupo extends MovimentoAnimale {

	private static final long serialVersionUID = -2442027714276181519L;

	public MovimentoLupo(Territorio origine, Territorio destinazione) {
		super(origine, destinazione);
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoLupo(super.getOrigine(), super.getDestinazione());
	}

}
