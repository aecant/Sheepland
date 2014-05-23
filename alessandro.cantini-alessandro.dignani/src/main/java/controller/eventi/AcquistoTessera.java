package controller.eventi;

import model.Tessera;
import controller.ClientMain;

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
