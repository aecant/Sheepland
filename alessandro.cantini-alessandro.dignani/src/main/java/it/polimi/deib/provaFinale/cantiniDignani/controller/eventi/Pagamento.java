package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

public class Pagamento extends Mossa {
	private static final long serialVersionUID = -7929296973460018330L;

	private Integer denaro;
	private String ricevente;
	private Giocatore[] giocatori;

	public Pagamento(int denaro, String pagante, String ricevente, Giocatore[] giocatori) {
		super(pagante);
		this.denaro = denaro;
		this.ricevente = ricevente;
		this.giocatori = giocatori;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setGiocatori(giocatori);
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().pagamento(denaro, giocatore, ricevente);
	}

}
