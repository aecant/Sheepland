package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public class UccisioneLupo implements Evento {
	private static final long serialVersionUID = -3007245624818726639L;

	private int territorio;
	private TipoAnimale tipoUcciso;
	private DatiTerritorio[] terrDaAggiornare;

	public UccisioneLupo(int territorio, TipoAnimale tipoUcciso, DatiTerritorio[] terrDaAggiornare) {
		this.territorio = territorio;
		this.tipoUcciso = tipoUcciso;
		this.terrDaAggiornare = terrDaAggiornare;
	}

	public void aggiornaDati() {
		MainClient.getDatiPartita().setTerritori(terrDaAggiornare);
	}

	public void visualizza() {
		MainClient.getUI().uccisioneLupo(territorio, tipoUcciso);
	}

	@Override
	public String toString() {
		return "UccisioneLupo [territorio=" + territorio + ", tipoUcciso=" + tipoUcciso + "]";
	}

}
