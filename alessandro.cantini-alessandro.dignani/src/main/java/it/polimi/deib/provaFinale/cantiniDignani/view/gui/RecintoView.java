package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecintoView extends JPanel {

	private static final long serialVersionUID = -8330627866329888205L;
	
	JLabel lblImmagine;
	
	public RecintoView(Point posizione, boolean isFinale) {
		this.setLayout(null);
		this.setBounds((posizione.x - CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width / 2), (posizione.y - CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height / 2), CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width, CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height);
		this.setOpaque(false);
		
		ImageIcon img;
		String nomeFile;
		
		if(isFinale) {
			nomeFile = "recintoFinale";
		} else {
			nomeFile = "recintoIniziale";
		}
		
		img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + nomeFile + ".png").getScaledInstance(CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width, CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height, Image.SCALE_SMOOTH));

		lblImmagine = new JLabel(img);
		lblImmagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_SEGNALINO_STRADA));
		
		add(lblImmagine);
	}
}
