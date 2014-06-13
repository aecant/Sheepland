package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DatiTerritorio implements Serializable {
	private static final long serialVersionUID = 7966573036032987276L;

	private Map<TipoAnimale, Integer> numeroAnimali;

	public DatiTerritorio() {
		numeroAnimali = new HashMap<TipoAnimale, Integer>();
	}

	protected void aggiungi(TipoAnimale tipo) {
		if (isLupo() && tipo == TipoAnimale.LUPO) {
			throw new IllegalArgumentException("Si sta cercando di aggiungere piu' di un lupo");
		}
		if (isPecoraNera() && tipo == TipoAnimale.PECORA_NERA) {
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
	 * Restituisce il numero di ovini (pecore, montoni e agnelli). La pecora
	 * nera non e' compresa.
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
	 * Restituisce una collezione di @link{TipoAnimale} contenente i tipi di ovino presenti sul
	 * territorio
	 * 
	 * @return la collezione dei @link{TipoAnimale} presenti sul territorio
	 */
	public Collection<TipoAnimale> getTipiAnimale() {
		return numeroAnimali.keySet();
	}

	public boolean isLupo() {
		return numeroAnimali.containsKey(TipoAnimale.LUPO) ? numeroAnimali.get(TipoAnimale.LUPO) > 0 : false;
	}

	public boolean isPecoraNera() {
		return numeroAnimali.containsKey(TipoAnimale.PECORA_NERA) ? numeroAnimali.get(TipoAnimale.PECORA_NERA) > 0 : false;
	}

}
