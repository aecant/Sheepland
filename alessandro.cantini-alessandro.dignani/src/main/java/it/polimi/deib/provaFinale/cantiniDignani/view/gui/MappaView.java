package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public class MappaView extends BackgroundMappaPanel{

	private static final long serialVersionUID = 7034340670757736701L;
	private ArrayList<PecoraView> pec = new ArrayList<PecoraView>();
	
	public ArrayList<PecoraView> getPec() {
		return pec;
	}

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "mappaSheepland.png"));
		this.setPreferredSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMaximumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMinimumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setLayout(null);
		
		// Temporaneo
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("new Point(" + e.getX() + ", " + e.getY() + "),");
			}
		});
	}
	
	public void creaPecora(Point coordinate) {
		PecoraView pecor = new PecoraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2));
		pec.add(pecor);
		add(pecor);
		pecor.getParent().repaint();
	}

	public void creaMontone(Point coordinate) {
		MontoneView mont = new MontoneView(coordinate.x-(CostantiGui.DIMENSIONE_MONTONE.width/2), coordinate.y-(CostantiGui.DIMENSIONE_MONTONE.height/2));
		add(mont);
		mont.getParent().repaint();
	}

	public void creaAgnello(Point coordinate) {
		AgnelloView agn = new AgnelloView(coordinate.x-(CostantiGui.DIMENSIONE_AGNELLO.width/2), coordinate.y-(CostantiGui.DIMENSIONE_AGNELLO.height/2));
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
}
