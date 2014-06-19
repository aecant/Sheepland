package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;

public class TrasformazioneAgnello implements Evento {

	private static final long serialVersionUID = -530628101226860884L;
	private boolean maschio;
	private Integer territorio;
	private DatiTerritorio[] terrDaAggiornare;

	public TrasformazioneAgnello(boolean maschio, Integer territorio, DatiTerritorio[] terrDaAggiornare) {
		this.maschio = maschio;
		this.territorio = territorio;
		this.terrDaAggiornare = terrDaAggiornare;
	}

	public void aggiornaDati() {
		MainClient.getDatiPartita().setTerritori(terrDaAggiornare);;
	}

	public void visualizza() {
		MainClient.getUI().trasformazioneAgnello(maschio, territorio);
	}

}
