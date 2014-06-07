package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class Pagamento extends Mossa {
	private static final long serialVersionUID = -7929296973460018330L;

	private Integer denaro;
	private String ricevente;

	public Pagamento(int denaro, String pagante, String ricevente) {
		super(pagante);
		this.denaro = denaro;
		this.ricevente = ricevente;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaGiocatori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().pagamento(denaro, super.getGiocatore(), ricevente);
	}

}
