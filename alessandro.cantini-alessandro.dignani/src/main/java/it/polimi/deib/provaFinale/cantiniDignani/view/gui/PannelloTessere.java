package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PannelloTessere extends JPanel {

	private static final long serialVersionUID = -594444461810094160L;

	private JPanel[] panelsTerreni = new JPanel[TipoTerritorio.values().length - 1];
	private JPanel[] panelsAngolini = new JPanel[TipoTerritorio.values().length - 1];
	private JLabel[] lblTerreni = new JLabel[TipoTerritorio.values().length - 1];
	private Image[] immaginiTerreni = new Image[TipoTerritorio.values().length - 1];

	public PannelloTessere() {
		super(new GridLayout(6, 1, 0, 0));

		// carico le immagini
		int cont = 0;
		for (TipoTerritorio t : TipoTerritorio.values()) {
			if (!t.equals(TipoTerritorio.SHEEPSBURG)) {
				immaginiTerreni[cont] = creaCasella(CostantiGui.PERCORSO_IMMAGINI + t.name().toLowerCase() + ".jpg");
				cont++;
			}
		}

		// inizializzo i panel e li inserisco
		for (int i = 0; i < immaginiTerreni.length; i++) {
			lblTerreni[i] = new JLabel("0");
			lblTerreni[i].setForeground(CostantiGui.COLORE_ACQUA);
			lblTerreni[i].setFont(CostantiGui.FONT_TERRENI);

			panelsAngolini[i] = new JPanel();
			panelsAngolini[i].setBounds(0, 0, CostantiGui.DIMENSIONE_ANGOLINI, CostantiGui.DIMENSIONE_ANGOLINI);
			panelsAngolini[i].setBackground(CostantiGui.COLORE_SFONDO_ANGOLINI);
			panelsAngolini[i].add(lblTerreni[i]);

			panelsTerreni[i] = new BackgroundPanel(immaginiTerreni[i]);
			panelsTerreni[i].setLayout(null);
			panelsTerreni[i].setBackground(CostantiGui.COLORE_ACQUA);
			panelsTerreni[i].add(panelsAngolini[i]);

			add(panelsTerreni[i], i);
		}
		this.setBackground(CostantiGui.COLORE_ACQUA);
		this.setPreferredSize(new Dimension(CostantiGui.DIMENSIONE_MAPPA.height/6, CostantiGui.DIMENSIONE_MAPPA.height));

	}

	private Image creaCasella(String percorsoImg) {
		return Toolkit.getDefaultToolkit().getImage(percorsoImg).getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, 0);
	}
}
