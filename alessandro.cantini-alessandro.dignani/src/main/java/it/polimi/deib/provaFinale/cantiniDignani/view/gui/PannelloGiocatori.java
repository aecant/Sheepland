package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PannelloGiocatori extends JPanel {
	static final long serialVersionUID = -8247537541354005432L;

	ArrayList<JPanel> panelsGiocatori;

	public PannelloGiocatori(Giocatore[] giocatori) {
		super(new GridLayout(giocatori.length, 1, 0, 0));
		setPreferredSize(CostantiGui.dimensionePannelloGiocatori);
		setBackground(CostantiGui.coloreAcqua);
		for (Giocatore g : giocatori) {
			panelsGiocatori.add(new GiocatoreView(g));
		}
	}
}
