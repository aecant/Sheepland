package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class TrasformazioneAgnello implements Evento {

	private static final long serialVersionUID = -530628101226860884L;
	private boolean maschio;
	private Integer territorio;

	public TrasformazioneAgnello(boolean maschio, Integer territorio) {
		this.maschio = maschio;
		this.territorio = territorio;
	}

	public boolean isMaschio() {
		return maschio;
	}

	public Integer getTerritorio() {
		return territorio;
	}

	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	public void visualizza() {
		ClientMain.getUI().trasformazioneAgnello(maschio, territorio);
	}

}
