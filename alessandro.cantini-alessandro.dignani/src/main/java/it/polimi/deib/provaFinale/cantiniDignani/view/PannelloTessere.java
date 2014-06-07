package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		super(new GridLayout(6, 1));

		// carico le immagini
		int cont = 0;
		for (TipoTerritorio t : TipoTerritorio.values()) {
			if (!t.equals(TipoTerritorio.SHEEPSBURG)) {
				immaginiTerreni[cont] = creaCasella(Costanti.percorsoImmagini + t.name().toLowerCase() + ".jpg");
				cont++;
			}
		}

		// inizializzo i panel e li inserisco
		for (int i = 0; i < immaginiTerreni.length; i++) {
			lblTerreni[i] = new JLabel("0");
			lblTerreni[i].setForeground(new Color(34, 145, 243));
			lblTerreni[i].setFont(new Font("Arial", Font.BOLD, 14));


			panelsAngolini[i] = new JPanel();
			panelsAngolini[i].setBounds(80, 80, 20, 20);
			panelsAngolini[i].setBackground(new Color(255, 255, 255, 180));
			panelsAngolini[i].add(lblTerreni[i]);

			panelsTerreni[i] = new BackgroundPanel(immaginiTerreni[i]);
			panelsTerreni[i].setLayout(null);
			panelsTerreni[i].setBackground(new Color(34, 145, 243));
			panelsTerreni[i].add(panelsAngolini[i]);

			this.add(panelsTerreni[i], i);
		}
		this.setBackground(new Color(48, 140, 235));
		this.setPreferredSize(new Dimension(100, 600));
		this.setMinimumSize(new Dimension(100, 600));

	}

	private Image creaCasella(String percorsoImg) {
		return Toolkit.getDefaultToolkit().getImage(percorsoImg).getScaledInstance(100, 100, 0);
	}
}
