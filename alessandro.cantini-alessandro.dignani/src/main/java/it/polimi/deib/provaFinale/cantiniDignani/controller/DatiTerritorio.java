package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

public class DatiTerritorio {
	private int numPecore, numMontoni, numAgnelli;
	private boolean lupo, pecoraNera;

	public DatiTerritorio() {
		this.numPecore = 0;
		this.numMontoni = 0;
		this.numAgnelli = 0;
		this.lupo = false;
		this.pecoraNera = false;
	}

	protected void aggiungiOvino(TipoOvino tipo) {
		switch (tipo) {
		case PECORA:
			numPecore++;
			break;
		case MONTONE:
			numMontoni++;
			break;
		case AGNELLO:
			numAgnelli++;
			break;
		case PECORANERA:
			pecoraNera = true;
			break;		
		}
	}
	
	protected void aggiungiLupo() {
		lupo = true;
	}

	public int getNumPecore() {
		return numPecore;
	}

	public int getNumMontoni() {
		return numMontoni;
	}

	public int getNumAgnelli() {
		return numAgnelli;
	}

	public boolean isLupo() {
		return lupo;
	}

	public boolean isPecoraNera() {
		return pecoraNera;
	}

}
