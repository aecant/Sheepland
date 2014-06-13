package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMossa extends JPanel {

	private static final long serialVersionUID = 4003349145527582509L;
	
	JButton bottone;
	ImageIcon sfondo;

	public PanelMossa(TipoMossa tipoMossa) {
		setLayout(new BorderLayout());
		setPreferredSize(CostantiGui.DIMENSIONE_PANEL_MOSSA);
		setSize(getPreferredSize());
		
		String percorso = CostantiGui.PERCORSO_IMMAGINI;
		percorso += tipoMossa.name().toLowerCase() + ".png";
		sfondo = new ImageIcon(Toolkit.getDefaultToolkit().getImage(percorso).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
		
		bottone = new JButton(sfondo);
		add(bottone, BorderLayout.CENTER);
		
		
		setOpaque(false);
	}
}
