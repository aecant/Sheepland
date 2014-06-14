package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LupoView extends PedinaView {

	private static final long serialVersionUID = 611823202675870719L;
	
	private JLabel immagine;

	public LupoView(int posizioneX, int posizioneY) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_LUPO);

		//setPreferredSize(CostantiGui.DIMENSIONE_LUPO);

		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "lupo.png").getScaledInstance(CostantiGui.DIMENSIONE_LUPO.width, CostantiGui.DIMENSIONE_LUPO.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_LUPO));
		add(immagine);
	}
	
	public LupoView(Point coordinate) {
		this(coordinate.x, coordinate.y);
	}
}
