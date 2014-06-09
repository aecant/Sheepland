package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;

public class SceltaPastore extends Mossa {

	private static final long serialVersionUID = 7981072203769321067L;
	private Pastore pastore;

	public SceltaPastore(String giocatore, Pastore pastore) {
		super(giocatore);
		this.pastore = pastore;
	}

	@Override
	public void aggiornaDati() {
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().sceltaPastore(getGiocatore(), pastore);
	}

	public Pastore getPastore() {
		return pastore;
	}

}
