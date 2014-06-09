package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.view.Costanti;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MappaView extends BackgroundMappaPanel{

	private static final long serialVersionUID = 7034340670757736701L;
	private PecoraView pec;
	
	public PecoraView getPec() {
		return pec;
	}

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(Costanti.percorsoImmagini + "mappaSheepland.png"));
		this.setPreferredSize(new Dimension(415, 600));
		this.setLayout(null);
		
		
		// Prova inserimento pecora in 40, 70
		pec = new PecoraView(40, 40);
		this.add(pec);
	}
}
