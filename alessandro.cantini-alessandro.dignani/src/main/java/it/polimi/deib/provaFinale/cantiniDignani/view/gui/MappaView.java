package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class MappaView extends BackgroundMappaPanel {

	private static final long serialVersionUID = 7034340670757736701L;

	Point[][] coordinateTerritori;
	Point[] coordinateStrade;

	List<TerritorioView> territoriView = new ArrayList<TerritorioView>();

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "mappaSheepland.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_MAPPA.width, CostantiGui.DIMENSIONE_MAPPA.height, Image.SCALE_SMOOTH));
		this.setPreferredSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMaximumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMinimumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setLayout(null);

		// Creo l'array con le coordinate scalate dei territori
		coordinateTerritori = new Point[Costanti.NUM_TERRITORI][TipoAnimale.values().length];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < TipoAnimale.values().length; j++) {
				coordinateTerritori[i][j] = new Point((int) (CostantiGui.COORDINATE_TERRITORI[i][j].getX() * CostantiGui.FATTORE_DI_SCALA),
						(int) (CostantiGui.COORDINATE_TERRITORI[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}

		// Creo l'array con le coordinate scalate delle strade
		coordinateStrade = new Point[Costanti.NUM_STRADE];
		for (int i = 0; i < Costanti.NUM_STRADE; i++) {
			coordinateStrade[i] = new Point((int) (CostantiGui.COORDINATE_STRADE[i].getX() * CostantiGui.FATTORE_DI_SCALA),
					(int) (CostantiGui.COORDINATE_STRADE[i].getY() * CostantiGui.FATTORE_DI_SCALA));
		}

		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			territoriView.add(new TerritorioView(i, coordinateTerritori[i], ClientMain.getDatiPartita().getTerritori()[i]));
		}
	}

	public void creaPecora(Point coordinate, int n) {
		PecoraView pecor = new PecoraView(coordinate.x - (CostantiGui.DIMENSIONE_PECORA.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_PECORA.height / 2), n);
		add(pecor);
		pecor.getParent().repaint();
	}

	public void creaMontone(Point coordinate, int n) {
		MontoneView mont = new MontoneView(coordinate.x - (CostantiGui.DIMENSIONE_MONTONE.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_MONTONE.height / 2), n);
		add(mont);
		mont.getParent().repaint();
	}

	public void creaAgnello(Point coordinate, int n) {
		AgnelloView agn = new AgnelloView(coordinate.x - (CostantiGui.DIMENSIONE_AGNELLO.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_AGNELLO.height / 2), n);
		add(agn);
		agn.getParent().repaint();
	}

	public void creaPecoraNera(Point coordinate) {
		PecoraNeraView pecoraNera = new PecoraNeraView(coordinate.x - (CostantiGui.DIMENSIONE_PECORA.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_PECORA.height / 2));
		add(pecoraNera);
		pecoraNera.getParent().repaint();
	}

	public void creaLupo(Point coordinate) {
		LupoView lupo = new LupoView(coordinate.x - (CostantiGui.DIMENSIONE_LUPO.width / 2), coordinate.y - (CostantiGui.DIMENSIONE_LUPO.height / 2));
		add(lupo);
		lupo.getParent().repaint();
	}

	public void creaPastore(int strada, ColorePastore colore) {
		Point coordinataStrada = coordinateStrade[strada];
		PastoreView past = new PastoreView(coordinataStrada.x - (CostantiGui.DIMENSIONE_PASTORE.width / 2), coordinataStrada.y - (CostantiGui.DIMENSIONE_PASTORE.height / 2), colore);
		add(past);
		past.getParent().repaint();
	}

	protected void disegnaTerritorio(Integer codTerritorio) {
		territoriView.get(codTerritorio).aggiorna();
		territoriView.get(codTerritorio).disegna();
	}

	public Point[][] getCoordinateTerritori() {
		return coordinateTerritori;
	}

	public Point[] getCoordinateStrade() {
		return coordinateStrade;
	}

	public void movimentoPecora(TipoAnimale tipoOvino, int origine, int destinazione) {
		Point orig = territoriView.get(origine).getCoordinate(tipoOvino);
		Point dest = territoriView.get(destinazione).getCoordinate(tipoOvino);
		PedinaView pedina;
		switch (tipoOvino) {
		case AGNELLO: pedina = new AgnelloView(orig, 0);
			break;
		case MONTONE: pedina = new MontoneView(orig, 0);
			break;
		case PECORA: pedina = new PecoraView(orig, 0);
			break;
		default: pedina = null;
			break;
		}
		
		territoriView.get(origine).aggiorna();
		territoriView.get(origine).disegna();
		pedina.muovi(dest);
		territoriView.get(destinazione).aggiorna();
		territoriView.get(destinazione).disegna();
	}
}
