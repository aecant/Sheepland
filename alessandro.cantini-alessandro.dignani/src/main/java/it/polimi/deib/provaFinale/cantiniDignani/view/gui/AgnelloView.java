package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AgnelloView extends PedinaConContatoreView {

	private static final long serialVersionUID = 8240587143214381489L;

	private JLabel immagine;

	public AgnelloView(int posizioneX, int posizioneY, Integer n) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_AGNELLO, n);
		
		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "agnello.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_AGNELLO.width, CostantiGui.DIMENSIONE_AGNELLO.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_AGNELLO));
		add(immagine);

		for (Component c : super.getComponents()) {
			if (c.getName() != null) {
				if (c.getName().equals("contatore")) {
					c.setBounds(7, 8, 15, 15);
				}
			}
		}
	}
	
	public AgnelloView(Point coordinate, Integer n) {
		this(coordinate.x, coordinate.y, n);
	}
}
