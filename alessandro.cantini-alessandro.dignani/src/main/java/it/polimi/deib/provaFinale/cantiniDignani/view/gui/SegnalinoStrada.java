package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SegnalinoStrada extends JPanel {

	private static final long serialVersionUID = 5190080600406868477L;
	
	private int codStrada;
	private JLabel lblImmagine;
	
	public SegnalinoStrada(Point posizione, boolean aPagamento, int codStrada) {
		this.codStrada = codStrada;
		this.setLayout(null);
		this.setBounds((posizione.x - CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width / 2), (posizione.y - CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height / 2), CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width, CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height);
		this.setOpaque(false);
		
		ImageIcon img;
		String nomeFile;
		
		if(aPagamento) {
			nomeFile = "segnalinoStradaPagamento";
		} else {
			nomeFile = "segnalinoStradaGratis";
		}
		
		img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + nomeFile + ".png").getScaledInstance(CostantiGui.DIMENSIONE_SEGNALINO_STRADA.width, CostantiGui.DIMENSIONE_SEGNALINO_STRADA.height, Image.SCALE_SMOOTH));

		lblImmagine = new JLabel(img);
		lblImmagine.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_SEGNALINO_STRADA));
		
		add(lblImmagine);
		
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if(c  instanceof SegnalinoStrada) {
					Gui.getCoda().aggiungi(((SegnalinoStrada) c).getCodStrada());
					for(SegnalinoStrada s : Gui.getFinestraPartita().getMappa().segnalini) {
						Gui.getFinestraPartita().getMappa().remove(s);
					}
					Gui.getFinestraPartita().getMappa().segnalini.clear();
					Gui.getFinestraPartita().getMappa().repaint();
				}
				
			}
		});
	}

	public int getCodStrada() {
		return codStrada;
	}
}
