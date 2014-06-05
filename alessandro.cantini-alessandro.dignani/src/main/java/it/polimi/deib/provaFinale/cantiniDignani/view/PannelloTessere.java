package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PannelloTessere extends JPanel {

	private static final long serialVersionUID = -594444461810094160L;

	JPanel[] panelsTerreni = new JPanel[TipoTerritorio.values().length - 1];
	JPanel[] panelsAngolini = new JPanel[TipoTerritorio.values().length - 1];
	JLabel[] lblTerreni = new JLabel[TipoTerritorio.values().length - 1];
	Image[] immaginiTerreni = new Image[TipoTerritorio.values().length - 1];

	public PannelloTessere() {
		super(new GridLayout(6, 1));

		// carico le immagini
		int cont = 0;
		for (TipoTerritorio t : TipoTerritorio.values()) {
			if (!t.equals(TipoTerritorio.SHEEPSBURG)) {
				immaginiTerreni[cont] = creaCasella("src/images/" + t.name().toLowerCase() + ".png");
				cont++;
			}
		}

		// inizializzo i panel e li inserisco
		for (int i = 0; i < immaginiTerreni.length; i++) {
			lblTerreni[i] = new JLabel("0");
			lblTerreni[i].setForeground(new Color(255, 255, 255));
			
			panelsAngolini[i] = new JPanel();
			panelsAngolini[i].setBounds(80, 80, 20, 20);
			panelsAngolini[i].setBackground(new Color(255, 0, 0, 120));
			panelsAngolini[i].add(lblTerreni[i]);
			
			panelsTerreni[i] = new BackgroundPanel(immaginiTerreni[i]);
			panelsTerreni[i].setLayout(null);
			panelsTerreni[i].setBackground(new Color(0, 200, 255));
			panelsTerreni[i].add(panelsAngolini[i]);
			
			this.add(panelsTerreni[i], i);
		}
		this.setBackground(new Color(0, 200, 255));
		this.setPreferredSize(new Dimension(100, 600));
		this.setMinimumSize(new Dimension(100, 600));

	}

	private Image creaCasella(String percorsoImg) {
		return Toolkit.getDefaultToolkit().getImage(percorsoImg).getScaledInstance(100, 100, 0);
	}
}
