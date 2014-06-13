package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

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
	}
	
	public void creaPecora(Point coordinate) {
		PecoraView temp = new PecoraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2));
		pec.add(temp);
		add(temp);
		temp.getParent().repaint();
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
		PecoraNeraView mont = new PecoraNeraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2));
		add(mont);
		mont.getParent().repaint();
	}

	public void creaLupo(Point coordinate) {
		LupoView mont = new LupoView(coordinate.x-(CostantiGui.DIMENSIONE_LUPO.width/2), coordinate.y-(CostantiGui.DIMENSIONE_LUPO.height/2));
		add(mont);
		mont.getParent().repaint();
	}
}
