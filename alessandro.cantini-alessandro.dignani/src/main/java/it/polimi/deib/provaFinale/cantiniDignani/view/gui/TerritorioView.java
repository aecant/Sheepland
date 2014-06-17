package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class TerritorioView {
	private Map<TipoAnimale, Point> coordAnimali;
	private final Point[] coordinate;
	private final int codTerr;
	private int indice;

	public TerritorioView(int codTerr, Point[] coordinate) {
		this.codTerr = codTerr;
		this.coordinate = coordinate;
		aggiorna();
	}

	/**
	 * Aggiorna la mappa delle coordinate degli animali
	 */
	public void aggiorna() {
		coordAnimali = new HashMap<TipoAnimale, Point>();
		indice = 0;
		for (TipoAnimale tipo : TipoAnimale.values()) {
			if (dati().getTipiAnimale().contains(tipo)) {
				coordAnimali.put(tipo, coordinate[indice]);
				indice++;
			}
		}
	}

	public Point getCoordinate(TipoAnimale tipo) {
		return coordAnimali.containsKey(tipo) ? coordAnimali.get(tipo) : coordinate[indice];
	}

	public void disegna() {
		for (TipoAnimale tipo : TipoAnimale.values()) {
			if (dati().getTipiAnimale().contains(tipo)) {
				disegnaAnimale(tipo, coordAnimali.get(tipo), dati().getNum(tipo));
			}
		}
	}

	private void disegnaAnimale(TipoAnimale tipo, Point point, int num) {
		switch (tipo) {
		case AGNELLO:
			Gui.getFinestraPartita().getMappa().creaAgnello(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case LUPO:
			Gui.getFinestraPartita().getMappa().creaLupo(point);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case MONTONE:
			Gui.getFinestraPartita().getMappa().creaMontone(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case PECORA:
			Gui.getFinestraPartita().getMappa().creaPecora(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case PECORA_NERA:
			Gui.getFinestraPartita().getMappa().creaPecoraNera(point);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		}
	}

	private DatiTerritorio dati() {
		return ClientMain.getDatiPartita().getTerritori()[codTerr];
	}
}
