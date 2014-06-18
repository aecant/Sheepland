package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class PannelloMosse extends JPanel {

	private static final long serialVersionUID = 5474628813953012904L;

	// List<PanelMossa> panelsMosse = new ArrayList<PanelMossa>();
	Map<TipoMossa, PanelMossa> panelsMosse = new HashMap<TipoMossa, PanelMossa>();

	public PannelloMosse() {
		super(new GridLayout(TipoMossa.values().length, 1, 0, 0));

		setBackground(CostantiGui.COLORE_ACQUA);

		int i = 0;
		for (TipoMossa tm : TipoMossa.values()) {
			PanelMossa p = new PanelMossa(tm);
			panelsMosse.put(tm, p);
			add(p, i++);
		}
	}

	/**
	 * Disabilita i bottoni delle mosse specificate nella lista
	 * 
	 * @param mosseNonDisponibili
	 *            lista delle mosse da disabilitare
	 */
	protected void disabilitaMosse(List<TipoMossa> mosseNonDisponibili) {
		for (TipoMossa tm : mosseNonDisponibili) {
			panelsMosse.get(tm).disabilitaBottone();
		}
	}

	/**
	 * Disabilita tutti i bottoni delle mosse
	 */
	protected void disabilitaMosse() {
		List<TipoMossa> mosseNonDisponibili = new ArrayList<TipoMossa>(panelsMosse.keySet());
		disabilitaMosse(mosseNonDisponibili);
	}

	/**
	 * Abilita i bottoni delle mosse specificate nella lista
	 * 
	 * @param mosseDisponibili
	 *            lista delle mosse da abilitare
	 */
	protected void abilitaMosse(List<TipoMossa> mosseDisponibili) {
		int n = 0;
		for (TipoMossa tm : mosseDisponibili) {
			panelsMosse.get(tm).abilitaBottone();
			panelsMosse.get(tm).impostaAscoltatore(n);
			n++;
		}
	}
}
