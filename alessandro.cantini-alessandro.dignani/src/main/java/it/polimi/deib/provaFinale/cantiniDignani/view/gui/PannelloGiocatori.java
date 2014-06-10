package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PannelloGiocatori extends JPanel {
	static final long serialVersionUID = -8247537541354005432L;

	List<JPanel> panelsGiocatori = new ArrayList<JPanel>();

	public PannelloGiocatori(Giocatore[] giocatori) {
		super(new GridLayout(giocatori.length, 1, 0, 10));

		setBackground(CostantiGui.COLORE_ACQUA);

		for (Giocatore g : giocatori) {
			panelsGiocatori.add(new GiocatoreView(g));
		}
		int i = 0;
		for (JPanel p : panelsGiocatori) {
			add(p, i++);
		}
	}
}
