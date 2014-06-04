package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;

public class MovimentoPastore extends Mossa{

	private static final long serialVersionUID = -8968808141497412233L;
	private Pastore pastore;
	private int origine, destinazione;
	
	public MovimentoPastore(String giocatore, Pastore pastore, int origine, int destinazione) {
		super(giocatore);
		this.pastore = pastore;
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public Pastore getPastore() {
		return pastore;
	}

	public int getOrigine() {
		return origine;
	}

	public int getDestinazione() {
		return destinazione;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaPastori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoPastore(pastore, origine, destinazione);
	}
	
}