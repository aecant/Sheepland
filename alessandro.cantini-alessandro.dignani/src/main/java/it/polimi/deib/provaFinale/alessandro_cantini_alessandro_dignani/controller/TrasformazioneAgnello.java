package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

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
		Client.getDatiPartita().aggiornaTerritori();
	}

	public void visualizza() {
		Client.getUI().trasformazioneAgnello(maschio, territorio);
	}

}
