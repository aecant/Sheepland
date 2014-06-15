package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class TerritorioView {
	private Map<TipoAnimale, Point> coordAnimali;
	private final Point[] coordinate;
	private final int codTerr;
	private DatiTerritorio dati;
	private int indice;
	
	public TerritorioView(int codTerr, Point[] coordinate, DatiTerritorio dati) {
		this.codTerr = codTerr;
		this.coordinate = coordinate;
		this.dati = dati;
		aggiorna();
	}
	
	/**
	 * Aggiorna la mappa delle coordinate degli animali
	 */
	public void aggiorna() {
		coordAnimali = new HashMap<TipoAnimale, Point>();
		indice = 0;
		for(TipoAnimale tipo : TipoAnimale.values()) {
			if(dati.getTipiAnimale().contains(tipo)) {
				coordAnimali.put(tipo, coordinate[indice]);
				indice++;
			}
		}
	}
	
	public Point getCoordinate(TipoAnimale tipo) {
		return coordAnimali.containsKey(tipo) ? coordAnimali.get(tipo) : coordinate[indice];
	}

	public int getCodTerr() {
		return codTerr;
	}

	public DatiTerritorio getDati() {
		return dati;
	}
}
