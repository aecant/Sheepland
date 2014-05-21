package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

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
		Client.getDatiPartita().aggiornaGiocatori();
	}

	@Override
	public void visualizza() {
		Client.getUI().pagamento(denaro, super.getGiocatore(), altroGiocatore);
	}

}
