package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public class AcquistoTessera extends Mossa {
	private static final long serialVersionUID = -8771805864958018539L;

	private final Tessera tessera;
	private final Tessera[] tessInCimaDaAggiornare;
	private final Giocatore[] giocDaAggiornare;

	public AcquistoTessera(String giocatore, Tessera tessera, Tessera[] tessInCimaDaAggiornare, Giocatore[] giocDaAggiornare) {
		super(giocatore);
		this.tessera = tessera;
		this.tessInCimaDaAggiornare = tessInCimaDaAggiornare.clone();
		this.giocDaAggiornare = giocDaAggiornare.clone();
	}

	@Override
	public void aggiornaDati() {
		MainClient.getDatiPartita().setGiocatori(giocDaAggiornare);
		MainClient.getDatiPartita().setTessereInCima(tessInCimaDaAggiornare);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().acquistoTessera(giocatore, tessera);
	}

}
