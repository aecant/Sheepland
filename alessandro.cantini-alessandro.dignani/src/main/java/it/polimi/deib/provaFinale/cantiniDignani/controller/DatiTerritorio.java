package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DatiTerritorio {
	private Map<TipoAnimale, Integer> numeroAnimali;

	public DatiTerritorio() {
		numeroAnimali = new HashMap<TipoAnimale, Integer>();
	}

	protected void aggiungi(TipoAnimale tipo) {
		if (isLupo() && tipo == TipoAnimale.LUPO) {
			throw new IllegalArgumentException("Si sta cercando di aggiungere piu' di un lupo");
		}
		if (isPecoraNera() && tipo == TipoAnimale.PECORANERA) {
			throw new IllegalArgumentException("Si sta cercando di aggiungere piu' di una pecora nera");
		}

		Utilita.incrementa(numeroAnimali, tipo);
	}

	public int getNumPecore() {
		return getNum(TipoAnimale.PECORA);
	}

	public int getNumMontoni() {
		return getNum(TipoAnimale.MONTONE);
	}

	public int getNumAgnelli() {
		return getNum(TipoAnimale.AGNELLO);
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
	 * Restituisce il numero di animali di un certo tipo presenti sul territorio
	 * 
	 * @param tipo
	 *            il tipo di animale di cui si vuole conoscere il numero
	 * @return il numero di anmali di un certo tipo presenti sul territorio
	 */
	public int getNum(TipoAnimale tipo) {
		return numeroAnimali.containsKey(tipo) ? numeroAnimali.get(tipo) : 0;
	}

	/**
	 * Restituisce un set di TipoOvino contenente i tipi di ovino presenti sul
	 * territorio
	 * 
	 * @return il set dei TipoOvino presenti sul territorio
	 */
	public Set<TipoAnimale> getTipiOvino() {
		return numeroAnimali.keySet();
	}

	public boolean isLupo() {
		return numeroAnimali.get(TipoAnimale.LUPO) > 0;
	}

	public boolean isPecoraNera() {
		return numeroAnimali.get(TipoAnimale.PECORANERA) > 0;
	}

}
