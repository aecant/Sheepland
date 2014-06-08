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
		TipoAnimale[] animaliPresenti = (TipoAnimale[]) coordAnimali.keySet().toArray();
		for(indice = 0; indice < animaliPresenti.length; indice++) {
			coordAnimali.put(animaliPresenti[indice], coordinate[indice]);
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
