package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.eventi;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.ClientMain;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;

public class MovimentoPastore extends Mossa{

	private static final long serialVersionUID = -8968808141497412233L;
	private Pastore pastore;
	private Strada origine, destinazione;
	
	public MovimentoPastore(String giocatore, Pastore pastore, Strada origine, Strada destinazione) {
		super(giocatore);
		this.pastore = pastore;
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public Pastore getPastore() {
		return pastore;
	}

	public Strada getOrigine() {
		return origine;
	}

	public Strada getDestinazione() {
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
