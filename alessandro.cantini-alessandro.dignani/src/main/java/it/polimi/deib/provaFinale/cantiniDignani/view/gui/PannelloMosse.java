package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PannelloMosse extends JPanel {

	private static final long serialVersionUID = 5474628813953012904L;

	List<JPanel> panelsMosse = new ArrayList<JPanel>();
	
	
	public PannelloMosse() {
		super(new GridLayout(TipoMossa.values().length, 1, 0, 0));
		
		setBackground(CostantiGui.COLORE_ACQUA);

		for(TipoMossa tm : TipoMossa.values()) {
			panelsMosse.add(new PanelMossa(tm));
		}
		int i = 0;
		for (JPanel p : panelsMosse) {
			add(p, i++);
		}
	}

}
