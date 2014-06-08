package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Animale;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.util.HashMap;
import java.util.Map;

public class GestoreFaseFinale {
	private Partita partita;

	public GestoreFaseFinale(Partita partita) {
		this.partita = partita;
	}

	/**
	 * Restituisce una mappa che associa a ogni tipo territorio il valore delle
	 * pecore che stanno sopra ai territori di quel tipo. Il valore corrisponde
	 * al numero delle pecore, la pecora nera vale il doppio
	 * 
	 * @return la mappa che associa a ogni tipo di territorio il suo valore
	 */
	public Map<TipoTerritorio, Integer> getValoriTerritori() {
		Map<TipoTerritorio, Integer> valori = new HashMap<TipoTerritorio, Integer>();
		for (TipoTerritorio tipo : TipoTerritorio.valoriTessere()) {
			valori.put(tipo, 0);
		}

		for (Pecora pec : partita.getGregge().getPecore()) {
			incrementa(valori, pec, 1);
		}

		incrementa(valori, partita.getGregge().getPecoraNera(), 2);

		return valori;
	}

	/**
	 * Aumenta il valore del tipo di territorio su cui sta un certo animale
	 * 
	 * @param mappa
	 *            la mappa che contiene l'associazione fra TipoTerritorio e
	 *            valore
	 * @param animale
	 *            l'animale il cui TipoTerritorio si vuole incrementare
	 * @param quantita
	 *            la quantita' di cui si vuole incrementare il valore
	 */
	private void incrementa(Map<TipoTerritorio, Integer> mappa, Animale animale, int quantita) {
		TipoTerritorio tipo = animale.getPosizione().getTipo();
		if (tipo == TipoTerritorio.SHEEPSBURG)
			return;
		mappa.put(tipo, mappa.get(tipo) + quantita);
	}
}
