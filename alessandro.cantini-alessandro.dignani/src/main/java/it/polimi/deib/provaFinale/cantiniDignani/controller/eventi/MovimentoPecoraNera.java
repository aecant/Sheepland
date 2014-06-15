package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;

public class MovimentoPecoraNera extends MovimentoAnimale {

	private static final long serialVersionUID = -742787287192836056L;

	public MovimentoPecoraNera(int origine, int destinazione, DatiTerritorio[] terrDaAggiornare) {
		super(origine, destinazione, terrDaAggiornare);
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoPecoraNera(origine, destinazione);
	}

}
