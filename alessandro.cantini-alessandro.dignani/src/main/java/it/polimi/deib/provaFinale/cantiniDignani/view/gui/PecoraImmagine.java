package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.view.Costanti;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraImmagine extends JLabel {

	private static final long serialVersionUID = -1630434376371077616L;

	public PecoraImmagine() {
		super(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Costanti.percorsoImmagini + "pecora.png").getScaledInstance(Costanti.dimensionePecora.width, Costanti.dimensionePecora.height, Image.SCALE_SMOOTH)));
		this.setPreferredSize(Costanti.dimensionePecora);
	}
}
