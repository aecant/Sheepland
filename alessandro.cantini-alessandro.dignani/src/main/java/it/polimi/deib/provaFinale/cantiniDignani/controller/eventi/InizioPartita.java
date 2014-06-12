package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class InizioPartita implements Evento {

	private static final long serialVersionUID = -5582597000875121887L;

	public void aggiornaDati() {
		ClientMain.aggiornaDatiPartita();
	}

	public void visualizza() {
		ClientMain.getUI().inizioPartita();
	}

	@Override
	public String toString(){
		return "Inizio partita";
	}
	
}
