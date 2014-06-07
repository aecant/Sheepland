package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.HashSet;
import java.util.Set;

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

	/**
	 * Restituisce il numero di ovini (pecore, montoni e agnelli)
	 * 
	 * @return il numero di ovini
	 */
	public int getNumOvini() {
		return numPecore + numAgnelli + numMontoni;
	}

	/**
	 * Restituisce un set di TipoOvino contenente i tipi di ovino presenti sul
	 * territorio
	 * 
	 * @return il set dei TipoOvino presenti sul territorio
	 */
	public Set<TipoOvino> getTipiOvino() {
		Set<TipoOvino> tipi = new HashSet<TipoOvino>();

		if (numPecore > 0) {
			tipi.add(TipoOvino.PECORA);
		}
		if (numMontoni > 0) {
			tipi.add(TipoOvino.PECORA);
		}
		if (numAgnelli > 0) {
			tipi.add(TipoOvino.PECORA);
		}
		if (pecoraNera) {
			tipi.add(TipoOvino.PECORANERA);
		}

		return tipi;
	}

	public boolean isLupo() {
		return lupo;
	}

	public boolean isPecoraNera() {
		return pecoraNera;
	}

}
