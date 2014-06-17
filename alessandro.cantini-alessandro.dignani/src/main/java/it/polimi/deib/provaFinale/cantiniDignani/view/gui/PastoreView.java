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
	int codStrada;

	public PastoreView(int posizioneX, int posizioneY, ColorePastore colore, int codStrada) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_PASTORE);

		this.codStrada = codStrada;
		
		String percorso = CostantiGui.PERCORSO_IMMAGINI;
		percorso += "pedina_" + colore + ".png";
		
		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(percorso).getScaledInstance(CostantiGui.DIMENSIONE_PASTORE.width, CostantiGui.DIMENSIONE_PASTORE.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PASTORE));
		add(immagine);
	}
	
	public PastoreView(Point coordinate, ColorePastore colore, int codStrada) {
		this(coordinate.x, coordinate.y, colore, codStrada);
	}

	public int getCodStrada() {
		return codStrada;
	}
}
