package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundMappaPanel extends JPanel {

	private static final long serialVersionUID = -3163543217049638252L;

	private Image img;

	public BackgroundMappaPanel(Image img) {
		super(new BorderLayout());
		this.img = img;
		this.setBackground(CostantiGui.coloreAcqua);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, (int)(this.getHeight()*0.8), this.getHeight(), this);
	}
}