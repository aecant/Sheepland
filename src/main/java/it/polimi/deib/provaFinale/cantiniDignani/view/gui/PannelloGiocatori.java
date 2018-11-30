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
	
	protected void aggiorna() {
		for(String g : panelsGiocatori.keySet()) {
			aggiorna(g);
		}
	}
	
	protected void aggiorna(String giocatore) {
		panelsGiocatori.get(giocatore).aggiorna();
	}
	
	protected void pagamento(Integer denaro, String pagante, String pagato) {
		aggiorna(pagante);
		aggiorna(pagato);
	}
	
	public void impostaGiocatoreCorrente(String nome) {
		for(String giocatore : panelsGiocatori.keySet()) {
			if(giocatore.equals(nome)) {
				panelsGiocatori.get(giocatore).setCorrente();
			} else {
				panelsGiocatori.get(giocatore).setNonCorrente();
			}
		}
		repaint();
	}
}
