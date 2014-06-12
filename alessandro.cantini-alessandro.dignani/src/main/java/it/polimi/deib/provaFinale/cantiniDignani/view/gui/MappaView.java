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
		addMouseListener(new java.awt.event.MouseAdapter() {
			int i = 0;
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(i%5==0) {
					System.out.print("},\n{");
				} else {
					System.out.print(", ");
				}
				System.out.print("new Point("+e.getX()+", "+e.getY()+")");
				i++;
			}
		});
	}
	
	public void disegnaPecora(Point coordinate) {
		PecoraView temp = new PecoraView(coordinate.x-(CostantiGui.DIMENSIONE_PECORA.width/2), coordinate.y-(CostantiGui.DIMENSIONE_PECORA.height/2));
		pec.add(temp);
		add(temp);
		temp.getParent().repaint();
	}
}
