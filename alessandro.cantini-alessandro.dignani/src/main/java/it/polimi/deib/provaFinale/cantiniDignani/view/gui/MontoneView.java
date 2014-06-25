package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Classe che si occupa della visualizzazione della pedina di tipo montone
 * @author alessandrodignani
 *
 */
public class MontoneView extends PedinaConContatoreView {

	private static final long serialVersionUID = -2111643752999646338L;

	public MontoneView(int posizioneX, int posizioneY, Integer n) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_MONTONE, n);

		JLabel immagine;

		immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "montone.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_MONTONE.width, CostantiGui.DIMENSIONE_MONTONE.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_MONTONE));
		add(immagine);
	}

	public MontoneView(Point coordinate, Integer n) {
		this(coordinate.x, coordinate.y, n);
	}
}
