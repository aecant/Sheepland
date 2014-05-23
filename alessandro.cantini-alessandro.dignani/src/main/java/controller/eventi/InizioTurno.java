package controller.eventi;

import controller.ClientMain;

public class InizioTurno extends Mossa {

	private static final long serialVersionUID = -1669276038393313888L;

	public InizioTurno(String giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setGiocatoreDiTurno(super.getGiocatore());
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().inizioTurno(super.getGiocatore());
	}

}
