package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public class PedinaListener extends JPanel {

	private static final long serialVersionUID = -1812003184298102780L;
	
	private Integer indice;
	public PedinaListener(Point posizione, Dimension dimensione, Integer indice) {
		this.indice = indice;
		this.setBounds(posizione.x - dimensione.width / 2, posizione.y - dimensione.height / 2, dimensione.width, dimensione.height);
		setLayout(null);
		setOpaque(false);
		
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if(c  instanceof PedinaListener) {
					Gui.getCoda().aggiungi(((PedinaListener) c).getIndice());
					for(PedinaListener s : Gui.getFinestraPartita().getMappa().ascoltatori) {
						Gui.getFinestraPartita().getMappa().remove(s);
					}
					Gui.getFinestraPartita().getMappa().ascoltatori.clear();
					Gui.getFinestraPartita().getMappa().repaint();
				}
			}
		});
	}

	public Integer getIndice() {
		return indice;
	}
}
