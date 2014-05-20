package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Animale;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;

public class MovimentoAnimale extends Evento {

	private static final long serialVersionUID = 3263700493723371773L;
	private Animale animale;
	private Territorio origine, destinazione;
	
	public MovimentoAnimale(Animale animale, Territorio origine, Territorio destinazione) {
		this.animale = animale;
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public Animale getAnimale() {
		return animale;
	}

	public Territorio getOrigine() {
		return origine;
	}

	public Territorio getDestinazione() {
		return destinazione;
	}
	
}
