package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DatiTerritorio {
	private Map<TipoAnimale, Integer> numeroAnimali;

	public DatiTerritorio() {
		numeroAnimali = new HashMap<TipoAnimale, Integer>();
		for (TipoAnimale tipo : TipoAnimale.values()) {
			numeroAnimali.put(tipo, 0);
		}
	}

	protected void aggiungi(TipoAnimale tipo) {
		Utilita.incrementa(numeroAnimali, tipo);
	}

	public int getNumPecore() {
		return numeroAnimali.get(TipoAnimale.PECORA);
	}

	public int getNumMontoni() {
		return numeroAnimali.get(TipoAnimale.MONTONE);
	}

	public int getNumAgnelli() {
		return numeroAnimali.get(TipoAnimale.AGNELLO);
	}

	/**
	 * Restituisce il numero di ovini (pecore, montoni e agnelli)
	 * 
	 * @return il numero di ovini
	 */
	public int getNumOvini() {
		return getNumPecore() + getNumAgnelli() + getNumMontoni();
	}

	/**
	 * Restituisce un set di TipoOvino contenente i tipi di ovino presenti sul
	 * territorio
	 * 
	 * @return il set dei TipoOvino presenti sul territorio
	 */
	public Set<TipoAnimale> getTipiOvino() {
		Set<TipoAnimale> tipi = new HashSet<TipoAnimale>();

		for (TipoAnimale t : numeroAnimali.keySet()) {
			if (numeroAnimali.get(t) > 0) {
				tipi.add(t);
			}
		}

		return tipi;
	}

	public boolean isLupo() {
		return numeroAnimali.get(TipoAnimale.LUPO) > 0;
	}

	public boolean isPecoraNera() {
		return numeroAnimali.get(TipoAnimale.PECORANERA) > 0;
	}

}
