package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PannelloGiocatori extends JPanel {
	static final long serialVersionUID = -8247537541354005432L;

	Map<String, GiocatoreView> panelsGiocatori = new HashMap<String, GiocatoreView>();

	public PannelloGiocatori(Giocatore[] giocatori) {
		super(new GridLayout(giocatori.length, 1, 0, 10));

		setBackground(CostantiGui.COLORE_ACQUA);

		for (Giocatore g : giocatori) {
			panelsGiocatori.put(g.getNome(), new GiocatoreView(g));
		}
		
		int i = 0;
		for (String giocatore : panelsGiocatori.keySet()) {
			add(panelsGiocatori.get(giocatore), i++);
		}
	}
	
	protected void aggiorna(String giocatore) {
		
	}
}
