package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.eventi;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.ClientMain;

public class Pagamento extends Mossa {
	private Integer denaro;
	private String altroGiocatore;
	
	public Pagamento(String giocatore, Integer denaro) {
		super(giocatore);
		this.denaro = denaro;
	}

	private static final long serialVersionUID = -7929296973460018330L;

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaGiocatori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().pagamento(denaro, super.getGiocatore(), altroGiocatore);
	}

}
