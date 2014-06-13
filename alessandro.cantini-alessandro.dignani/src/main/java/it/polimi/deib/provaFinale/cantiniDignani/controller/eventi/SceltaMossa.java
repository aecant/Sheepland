package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

public class SceltaMossa extends Mossa {

	private static final long serialVersionUID = -705406266520094747L;
	private TipoMossa mossa;

	public SceltaMossa(String giocatore, TipoMossa mossa) {
		super(giocatore);
		this.mossa = mossa;
	}

	public TipoMossa getMossa() {
		return mossa;
	}

	@Override
	public void aggiornaDati() {
	}

	@Override
	public void visualizza() {
	}

	@Override
	public String toString() {
		return getGiocatore() + " ha deciso di effettuare la mossa: " + mossa;
	}
}
