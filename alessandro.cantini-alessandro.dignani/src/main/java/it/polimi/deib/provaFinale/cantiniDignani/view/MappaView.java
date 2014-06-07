package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MappaView extends BackgroundPanel{

	private static final long serialVersionUID = 7034340670757736701L;

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(Costanti.percorsoImmagini + "mappaSheepland.png"));
		this.setPreferredSize(new Dimension(415, 600));
		this.setLayout(null);
		
		
		// Prova inserimento pecora in 40, 70
		PecoraView pec = new PecoraView(40, 70);
		this.add(pec);
	}

}
