package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaPosizionePastore implements Evento {

	private static final long serialVersionUID = 708070364097104922L;
	
	private int[] stradeLibere;

	public RichiestaPosizionePastore(int[] stradeLibere) {
		this.stradeLibere = stradeLibere;
	}

	public void aggiornaDati() {
	}

	public void visualizza() {
		ClientMain.getUI().sceltaPosizionePastore(stradeLibere);
	}

}
