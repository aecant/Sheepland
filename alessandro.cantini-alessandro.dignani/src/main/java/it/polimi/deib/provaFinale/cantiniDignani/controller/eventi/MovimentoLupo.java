package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class MovimentoLupo extends MovimentoAnimale {

	private static final long serialVersionUID = -2442027714276181519L;

	public MovimentoLupo(int origine, int destinazione) {
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

	@Override
	public String toString() {
		return "Movimento lupo da " + super.getOrigine() + " a " + super.getDestinazione();
	}
}
