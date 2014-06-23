package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraView extends PedinaConContatoreView {

	private static final long serialVersionUID = 4393985322855180188L;

	public PecoraView(int posizioneX, int posizioneY, Integer n) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_PECORA, n);

		JLabel pi;

		pi = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "pecora.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_PECORA.width, CostantiGui.DIMENSIONE_PECORA.height, Image.SCALE_SMOOTH)));
		pi.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PECORA));

		this.add(pi);

	}

	public PecoraView(Point coordinate, Integer n) {
		this(coordinate.x, coordinate.y, n);
	}
}
