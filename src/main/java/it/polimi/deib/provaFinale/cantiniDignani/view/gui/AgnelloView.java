package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * classe che si occupa della visualizzazione di un agnello
 * @author alessandrodignani
 *
 */
public class AgnelloView extends PedinaConContatoreView {

	private static final String CONTATORE = "contatore";

	private static final long serialVersionUID = 8240587143214381489L;

	/**
	 * Costruttore che crea un'istanza di agnello
	 * @param posizioneX coordinata x di dove posizionare l'agnello
	 * @param posizioneY coordinata y di dove posizionare l'agnello
	 * @param n numero che comparirà nella label
	 */
	public AgnelloView(int posizioneX, int posizioneY, Integer n) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_AGNELLO, n);

		JLabel immagine = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "agnello.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_AGNELLO.width, CostantiGui.DIMENSIONE_AGNELLO.height, Image.SCALE_SMOOTH)));
		immagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_AGNELLO));
		add(immagine);

		for (Component c : super.getComponents()) {
			if (c.getName() != null && c.getName().equals(CONTATORE)) {
				c.setBounds(7, 8, 15, 15);
			}
		}
	}

	/**
	 * Costruttore che crea un'istanza di agnello
	 * @param coordinate punto in cui inserire l'agnello
	 * @param n numero visualizzato dalla label
	 */
	public AgnelloView(Point coordinate, Integer n) {
		this(coordinate.x, coordinate.y, n);
	}
}
