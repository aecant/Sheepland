package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Classe che crea un panel con l'immagine passata come sfondo disegnandola con le proporzioni della mappa
 * @author alessandrodignani
 *
 */
public class BackgroundMappaPanel extends JPanel {

	private static final long serialVersionUID = -3163543217049638252L;

	private Image img;

	
	protected BackgroundMappaPanel(Image img) {
		super(new BorderLayout());
		this.img = img;
		this.setBackground(CostantiGui.COLORE_ACQUA);
	}

	@Override
	/**
	 * metodo sovrascritto per disegnare anche lo sfondo
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, (int) ((this.getWidth()-(this.getHeight()*0.75))/2), 0, (int)(this.getHeight()*0.75), this.getHeight(), this);
	}
}