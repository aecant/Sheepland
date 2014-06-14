package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraNeraView extends PedinaView {

	private static final long serialVersionUID = -335615034409783586L;

	private JLabel immagine;

	public PecoraNeraView(int posizioneX, int posizioneY) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_PECORA);

		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "pecora_nera.png").getScaledInstance(CostantiGui.DIMENSIONE_PECORA.width, CostantiGui.DIMENSIONE_PECORA.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PECORA));
		add(immagine);
	}
	
	public PecoraNeraView(Point coordinate) {
		this(coordinate.x, coordinate.y);
	}
}
