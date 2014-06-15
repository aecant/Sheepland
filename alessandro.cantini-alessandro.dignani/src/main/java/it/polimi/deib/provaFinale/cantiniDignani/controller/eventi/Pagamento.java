package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

public class Pagamento extends Mossa {
	private static final long serialVersionUID = -7929296973460018330L;

	private Integer denaro;
	private String ricevente;
	private Giocatore[] giocDaAggiornare;

	public Pagamento(int denaro, String pagante, String ricevente, Giocatore[] giocDaAggiornare) {
		super(pagante);
		this.denaro = denaro;
		this.ricevente = ricevente;
		this.giocDaAggiornare = giocDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setGiocatori(giocDaAggiornare);
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().pagamento(denaro, giocatore, ricevente);
	}

}
