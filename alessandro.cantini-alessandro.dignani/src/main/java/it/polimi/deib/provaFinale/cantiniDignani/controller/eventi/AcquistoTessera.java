package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public class AcquistoTessera extends Mossa {
	private static final long serialVersionUID = -8771805864958018539L;
	
	Tessera tessera;

	public AcquistoTessera(String giocatore, Tessera tessera) {
		super(giocatore);
		this.tessera = tessera;
	}

	public Tessera getTessera() {
		return tessera;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTessereInCima();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().acquistoTessera(super.getGiocatore(), getTessera());
	}

}
