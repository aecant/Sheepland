package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
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

	public boolean isMaschio() {
		return maschio;
	}

	public Integer getTerritorio() {
		return territorio;
	}

	public void aggiornaDati() {
		ClientMain.getDatiPartita().setTerritori(terrDaAggiornare);;
	}

	public void visualizza() {
		ClientMain.getUI().trasformazioneAgnello(maschio, territorio);
	}

}
