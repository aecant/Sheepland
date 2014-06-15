package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class MappaView extends BackgroundMappaPanel{

	private static final long serialVersionUID = 7034340670757736701L;
	
	Point[][] coordinateTerritori;
	
	List<TerritorioView> territoriView = new ArrayList<TerritorioView>();

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "mappaSheepland.png").getScaledInstance(CostantiGui.DIMENSIONE_MAPPA.width, CostantiGui.DIMENSIONE_MAPPA.height, Image.SCALE_SMOOTH));
		this.setPreferredSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMaximumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMinimumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setLayout(null);
		
		// Creo l'array con le coordinate scalate
		coordinateTerritori = new Point[Costanti.NUM_TERRITORI][TipoAnimale.values().length];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < TipoAnimale.values().length; j++) {
				coordinateTerritori[i][j] = new Point((int) (CostantiGui.COORDINATE_TERRITORI[i][j].getX() * CostantiGui.FATTORE_DI_SCALA),
						(int) (CostantiGui.COORDINATE_TERRITORI[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}
		
		for(int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			territoriView.add(new TerritorioView(i, coordinateTerritori[i], ClientMain.getDatiPartita().getTerritori()[i]));
		}
	}
	
	public void creaPecora(Point coordinate) {
		PecoraView pecor = new PecoraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2), 1);
		add(pecor);
		pecor.getParent().repaint();
	}

	public void creaMontone(Point coordinate) {
		MontoneView mont = new MontoneView(coordinate.x-(CostantiGui.DIMENSIONE_MONTONE.width/2), coordinate.y-(CostantiGui.DIMENSIONE_MONTONE.height/2), 1);
		add(mont);
		mont.getParent().repaint();
	}

	public void creaAgnello(Point coordinate) {
		AgnelloView agn = new AgnelloView(coordinate.x-(CostantiGui.DIMENSIONE_AGNELLO.width/2), coordinate.y-(CostantiGui.DIMENSIONE_AGNELLO.height/2), 1);
		add(agn);
		agn.getParent().repaint();
	}

	public void creaPecoraNera(Point coordinate) {
		PecoraNeraView pecoraNera = new PecoraNeraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2));
		add(pecoraNera);
		pecoraNera.getParent().repaint();
	}

	public void creaLupo(Point coordinate) {
		LupoView lupo = new LupoView(coordinate.x-(CostantiGui.DIMENSIONE_LUPO.width/2), coordinate.y-(CostantiGui.DIMENSIONE_LUPO.height/2));
		add(lupo);
		lupo.getParent().repaint();
	}
	
	public void creaPastore(Point coordinate, ColorePastore colore) {
		PastoreView past = new PastoreView(coordinate.x-(CostantiGui.DIMENSIONE_PASTORE.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PASTORE.height/2), colore);
		add(past);
		past.getParent().repaint();
	}
	
	protected void disegnaTerritorio(Integer codTerritorio) {
		
	}
}
