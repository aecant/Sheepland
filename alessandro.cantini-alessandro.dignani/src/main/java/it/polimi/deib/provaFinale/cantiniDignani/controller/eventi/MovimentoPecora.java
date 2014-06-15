package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public class MovimentoPecora extends Mossa {
	private static final long serialVersionUID = -5815440396346581844L;
	
	private TipoAnimale tipo;
	private int origine, destinazione;
	private DatiTerritorio[] terrDaAggiornare;

	public MovimentoPecora(String giocatore, TipoAnimale tipo, int origine, int destinazione, DatiTerritorio[] terrDaAggiornare) {
		super(giocatore);
		this.tipo = tipo;
		this.origine = origine;
		this.destinazione = destinazione;
		this.terrDaAggiornare = terrDaAggiornare;
	}
	
	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setTerritori(terrDaAggiornare);;
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoPecora(giocatore, tipo, origine, destinazione);
	}

}
