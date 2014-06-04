package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = -3163543217049638252L;
	
	private Image img;

	public BackgroundPanel(Image img) {
		super(new BorderLayout());
		this.img = img;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}