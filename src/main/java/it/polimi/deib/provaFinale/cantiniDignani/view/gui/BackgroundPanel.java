package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Classe che estende il JPanel con in più un'immagine come sfondo
 * @author alessandrodignani
 *
 */
public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = -3163543217049638252L;

	private Image img;

	protected BackgroundPanel(Image img) {
		super(new BorderLayout());
		this.img = img;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}