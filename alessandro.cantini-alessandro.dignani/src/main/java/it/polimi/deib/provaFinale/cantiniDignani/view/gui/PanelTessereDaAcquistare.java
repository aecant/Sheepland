package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTessereDaAcquistare extends JDialog {

	private static final long serialVersionUID = 2090429027013450871L;
	
	private JLabel lblMessaggio = new JLabel("Scegli la tessera da acquistare");
	private JPanel panelTessere = new JPanel();
	
	protected PanelTessereDaAcquistare() {
		super(Gui.finestraPartita);
		setSize(CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA);
		setLocationRelativeTo(getParent());
		setBounds(CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width / 2, CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height / 2, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height);
		setLayout(new BorderLayout());
		
		// Imposto il lblMessaggio
		lblMessaggio.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessaggio.setVerticalAlignment(SwingConstants.CENTER);
		lblMessaggio.setFont(CostantiGui.FONT_MESSAGGIO_ACQUISTO_TESSERA);
		lblMessaggio.setForeground(CostantiGui.COLORE_TESTO_MESSAGGI);
		
		// Imposto il panelTessere
		panelTessere.setLayout(new GridLayout(1, 2, 5, 0));
		panelTessere.setBackground(Color.WHITE);
		panelTessere.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERE_ACQUISTO);
		
		add(lblMessaggio, BorderLayout.NORTH);
		
		setVisible(false);
	}
	
	public void sceltaTessera(List<Tessera> tessereDisponibili) {
		setVisible(true);
		// TODO da completare
		Gui.getCoda().aggiungi(0);
	}
}
