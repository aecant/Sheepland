package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.rete.DatiPartita;

public class InizioPartita implements Evento {

	private static final long serialVersionUID = -5582597000875121887L;
	
	private DatiPartita dati;

	public InizioPartita(DatiPartita dati) {
		this.dati = dati;
	}

	public void aggiornaDati() {
		ClientMain.setDatiPartita(dati);
	}

	public void visualizza() {
		ClientMain.getUI().inizioPartita();
	}

}
