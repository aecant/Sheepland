package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PastoreView extends PedinaView {

	private static final long serialVersionUID = -335615034409783586L;

	private JLabel immagine;

	public PastoreView(int posizioneX, int posizioneY, ColorePastore colore) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_PASTORE);

		//setPreferredSize(CostantiGui.DIMENSIONE_PASTORE);
		
		String percorso = CostantiGui.PERCORSO_IMMAGINI;
		
		switch(colore) {
		case BLU: percorso += "pedina_blu.png";
		break;
		case ROSSO: percorso += "pedina_rosso.png";
		break;
		case GIALLO: percorso += "pedina_giallo.png";
		break;
		case VERDE: percorso += "pedina_verde.png";
		break;
		}
		
		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(percorso).getScaledInstance(CostantiGui.DIMENSIONE_PASTORE.width, CostantiGui.DIMENSIONE_PASTORE.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PASTORE));
		add(immagine);
	}
	
	public PastoreView(Point coordinate, ColorePastore colore) {
		this(coordinate.x, coordinate.y, colore);
	}
}
