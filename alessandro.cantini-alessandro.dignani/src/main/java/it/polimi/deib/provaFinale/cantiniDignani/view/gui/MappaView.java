package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Toolkit;

public class MappaView extends BackgroundMappaPanel{

	private static final long serialVersionUID = 7034340670757736701L;
	private PecoraView pec;
	
	public PecoraView getPec() {
		return pec;
	}

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(CostantiGui.percorsoImmagini + "mappaSheepland.png"));
		this.setPreferredSize(CostantiGui.dimensioneMappa);
		this.setMaximumSize(CostantiGui.dimensioneMappa);
		this.setLayout(null);
		
		// Prova inserimento pecora in 40, 70
		pec = new PecoraView(40, 40);
		this.add(pec);
	}
}
