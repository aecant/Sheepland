package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public class AcquistoTessera extends Mossa {
	private static final long serialVersionUID = -8771805864958018539L;

	private Tessera tessera;
	private Tessera[] tessInCimaDaAggiornare;

	public AcquistoTessera(String giocatore, Tessera tessera, Tessera[] tessInCimaDaAggiornare) {
		super(giocatore);
		this.tessera = tessera;
		this.tessInCimaDaAggiornare = tessInCimaDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		MainClient.getDatiPartita().setTessereInCima(tessInCimaDaAggiornare);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().acquistoTessera(giocatore, tessera);
	}

}
