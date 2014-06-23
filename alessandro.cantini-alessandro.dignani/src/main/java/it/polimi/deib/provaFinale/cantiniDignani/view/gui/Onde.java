package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Onde extends PedinaView implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Onde.class.getName());

	private static final long serialVersionUID = 7629687084080358822L;

	private static final ImageIcon immOnda = new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "onde.png")
			.getScaledInstance(CostantiGui.DIMENSIONE_ONDE.width, CostantiGui.DIMENSIONE_ONDE.height, Image.SCALE_SMOOTH));

	public Onde(Point coordinate) {
		super(coordinate, CostantiGui.DIMENSIONE_ONDE);

		JLabel immagineOnda;

		immagineOnda = new JLabel(immOnda);
		immagineOnda.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_ONDE));

		this.add(immagineOnda);
	}

	public Onde(int i, int j) {
		this(new Point(i, j));
	}

	public void run() {
		Point posIniziale = getLocation();
		double a = 0;
		while (true) {
			int offset = (int) (Math.sin(a) * CostantiGui.SPOSTAMENTO_ONDA);
			a += 0.1;
			setLocation(posIniziale.x + CostantiGui.SPOSTAMENTO_ONDA + offset, getLocation().y);
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "L'onda ha creato un bug inaspettato", e);
			}
		}
	}
}
