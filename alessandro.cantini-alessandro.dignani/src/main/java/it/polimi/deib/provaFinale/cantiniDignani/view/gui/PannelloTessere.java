package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class PannelloTessere extends JPanel {

	private static final long serialVersionUID = -594444461810094160L;

	private List<JPanel> tessereView = new ArrayList<JPanel>();

	public PannelloTessere(Tessera[] tessere, Map<TipoTerritorio, Integer> tessereGiocatoreCorrente) {
		super(new GridLayout(6, 1, 0, 0));

		// creo le TessereView
		int cont = 0;
		for (Tessera tess : tessere) {
				tessereView.add(new TesseraView(creaCasella(CostantiGui.PERCORSO_IMMAGINI + tess.getTipo().name().toLowerCase() + ".jpg"), tessere[cont].getCosto(), tessereGiocatoreCorrente.get(tess.getTipo())));
				cont++;
		}

		// inserisco i panel delle TessereView
		for (int i = 0; i < TipoTerritorio.valoriTessere().length; i++) {
			add(tessereView.get(i), i);
		}
		this.setBackground(CostantiGui.COLORE_ACQUA);
		this.setPreferredSize(new Dimension(CostantiGui.DIMENSIONE_MAPPA.height / TipoTerritorio.valoriTessere().length, CostantiGui.DIMENSIONE_MAPPA.height));

	}

	private Image creaCasella(String percorsoImg) {
		return Toolkit.getDefaultToolkit().getImage(percorsoImg).getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
	}
}
