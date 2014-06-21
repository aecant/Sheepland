package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;

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
		bottone.setEnabled(false);
		
		add(bottone, BorderLayout.CENTER);
		
		
		setOpaque(false);
	}
	
	protected void abilitaBottone() {
		bottone.setEnabled(true);
	}
	
	protected void disabilitaBottone() {
		bottone.setEnabled(false);
		for(MouseListener ml : bottone.getMouseListeners()) {
			bottone.removeMouseListener(ml);
		}
	}
	
	protected void impostaAscoltatore(final int n) {
		if(bottone.getMouseListeners().length != 0) {
			bottone.removeMouseListener(bottone.getMouseListeners()[0]);
			//Gui.finestraPartita.getPanelMessaggi().visualizzaMessaggio("ho rimosso il listener 0 di " + bottone.getMouseListeners().length);
		}
		bottone.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Gui.getCoda().aggiungi(n);
				Gui.getFinestraPartita().getPanelMosse().disabilitaMosse();
			}
		});
	}
}
