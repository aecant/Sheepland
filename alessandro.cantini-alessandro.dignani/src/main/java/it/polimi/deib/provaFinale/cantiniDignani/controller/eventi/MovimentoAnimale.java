package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;

public abstract class MovimentoAnimale implements Evento {
	private static final long serialVersionUID = 3263700493723371773L;
	
	protected int origine, destinazione;
	protected DatiTerritorio[] terrDaAggiornare;

	public MovimentoAnimale(int origine, int destinazione, DatiTerritorio[] terrDaAggiornare) {
		this.origine = origine;
		this.destinazione = destinazione;
		this.terrDaAggiornare = terrDaAggiornare.clone();
	}

	public final void aggiornaDati() {
		MainClient.getDatiPartita().setTerritori(terrDaAggiornare);
	}

	public abstract void visualizza();

}
