package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraImmagine extends JLabel {

	private static final long serialVersionUID = -1630434376371077616L;

	public PecoraImmagine() {
		super(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.percorsoImmagini + "pecora.png").getScaledInstance(CostantiGui.dimensionePecora.width, CostantiGui.dimensionePecora.height, Image.SCALE_SMOOTH)));
		this.setPreferredSize(CostantiGui.dimensionePecora);
	}
}
