package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerritorioView {
	private Map<TipoAnimale, Point> coordAnimali = new HashMap<TipoAnimale, Point>();
	private final Point[] coordinate;
	private final int codTerr;
	private int indice;
	
	private List<PedinaView> pedineTerritorio = new ArrayList<PedinaView>();

	
	public TerritorioView(int codTerr, Point[] coordinate) {
		this.codTerr = codTerr;
		this.coordinate = coordinate.clone();
		aggiorna();
	}

	/**
	 * Aggiorna la mappa delle coordinate degli animali
	 */
	public final void aggiorna() {
		indice = coordAnimali.keySet().size();
		for (TipoAnimale tipo : TipoAnimale.values()) {
			if (dati().getTipiAnimale().contains(tipo) && !coordAnimali.containsKey(tipo)) {
				coordAnimali.put(tipo, coordinate[indice]);
				indice++;
			}
		}
	}

	public Point getCoordinate(TipoAnimale tipo) {
		return coordAnimali.containsKey(tipo) ? coordAnimali.get(tipo) : coordinate[indice];
	}

	public void disegna() {
		for(PedinaView p : pedineTerritorio) {
			Gui.getFinestraPartita().getMappa().remove(p);
		}
		Gui.getFinestraPartita().getMappa().repaint();
		pedineTerritorio.clear();
		
		for (TipoAnimale tipo : TipoAnimale.values()) {
			if (dati().getTipiAnimale().contains(tipo)) {
				disegnaAnimale(tipo, coordAnimali.get(tipo), dati().getNum(tipo));
			}
		}
	}

	private void disegnaAnimale(TipoAnimale tipo, Point point, int num) {
		switch (tipo) {
		case AGNELLO:
			creaAgnello(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case LUPO:
			creaLupo(point);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case MONTONE:
			creaMontone(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case PECORA:
			creaPecora(point, num);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		case PECORA_NERA:
			creaPecoraNera(point);
			Gui.getFinestraPartita().getMappa().repaint();
			break;
		default: 
			break;
		}
	}

	private DatiTerritorio dati() {
		return MainClient.getDatiPartita().getTerritori()[codTerr];
	}
	
	public void creaPecora(Point coordinate, int n) {
		PecoraView pecor = new PecoraView(coordinate.x - (CostantiGui.DIMENSIONE_PECORA.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_PECORA.height / 2), n);
		pedineTerritorio.add(pecor);
		Gui.getFinestraPartita().getMappa().add(pecor);
		pecor.getParent().repaint();
	}

	public void creaMontone(Point coordinate, int n) {
		MontoneView mont = new MontoneView(coordinate.x - (CostantiGui.DIMENSIONE_MONTONE.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_MONTONE.height / 2), n);
		pedineTerritorio.add(mont);
		Gui.getFinestraPartita().getMappa().add(mont);
		mont.getParent().repaint();
	}

	public void creaAgnello(Point coordinate, int n) {
		AgnelloView agn = new AgnelloView(coordinate.x - (CostantiGui.DIMENSIONE_AGNELLO.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_AGNELLO.height / 2), n);
		pedineTerritorio.add(agn);
		Gui.getFinestraPartita().getMappa().add(agn);
		agn.getParent().repaint();
	}

	public void creaPecoraNera(Point coordinate) {
		PecoraNeraView pecoraNera = new PecoraNeraView(coordinate.x - (CostantiGui.DIMENSIONE_PECORA.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_PECORA.height / 2));
		pedineTerritorio.add(pecoraNera);
		Gui.getFinestraPartita().getMappa().add(pecoraNera);
		pecoraNera.getParent().repaint();
	}

	public void creaLupo(Point coordinate) {
		LupoView lupo = new LupoView(coordinate.x - (CostantiGui.DIMENSIONE_LUPO.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_LUPO.height / 2));
		pedineTerritorio.add(lupo);
		Gui.getFinestraPartita().getMappa().add(lupo);
		lupo.getParent().repaint();
	}
	
	public PecoraNeraView getPecoraNera() {
		for(PedinaView p : pedineTerritorio) {
			if (p instanceof PecoraNeraView) {
				return (PecoraNeraView) p;
			}
		}
		return null;
	}

	public PedinaView getLupo() {
		for(PedinaView p : pedineTerritorio) {
			if (p instanceof LupoView) {
				return (LupoView) p;
			}
		}
		return null;
	}

	public Point getCoordinataCentrale() {
		return coordinate[0];
	}
}
