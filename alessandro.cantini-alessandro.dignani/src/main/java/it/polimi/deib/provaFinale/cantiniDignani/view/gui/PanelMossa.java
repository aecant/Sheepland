package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelMossa extends JPanel {

	private static final long serialVersionUID = 4003349145527582509L;
	
	JButton bottone;

	public PanelMossa(TipoMossa tipoMossa) {
		setLayout(new BorderLayout());
		bottone = new JButton(tipoMossa.nome);
		add(bottone, BorderLayout.CENTER);
		setPreferredSize(CostantiGui.DIMENSIONE_PANEL_MOSSA);
		setOpaque(false);
	}
}
