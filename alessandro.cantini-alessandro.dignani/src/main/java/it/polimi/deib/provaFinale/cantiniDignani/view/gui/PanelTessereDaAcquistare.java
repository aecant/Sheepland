package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTessereDaAcquistare extends JPanel {

	private static final long serialVersionUID = 2090429027013450871L;
	
	private JLabel lblMessaggio = new JLabel("Scegli la tessera da acquistare");
	private JPanel panelTessere = new JPanel();
	
	protected PanelTessereDaAcquistare() {
		setBounds(CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width / 2, CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height / 2, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height);
		setLayout(new BorderLayout());

		
		// Imposto il lblMessaggio
		lblMessaggio.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessaggio.setVerticalAlignment(SwingConstants.CENTER);
		lblMessaggio.setFont(CostantiGui.FONT_MESSAGGIO_ACQUISTO_TESSERA);
		lblMessaggio.setForeground(CostantiGui.COLORE_TESTO_MESSAGGI);
		
		// Imposto il panelTessere
		panelTessere.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		panelTessere.setBackground(Color.WHITE);
		panelTessere.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERE_ACQUISTO);
		
		add(lblMessaggio, BorderLayout.NORTH);
		add(panelTessere, BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	public void sceltaTessera(List<Tessera> tessereDisponibili) {
		int cont = 0;
		for(Tessera tess : tessereDisponibili) {
			Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + tess.getTipo().name().toLowerCase() + ".jpg").getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
			TesseraView tessView = new TesseraView(img, tess.getCosto(), null, cont);
			tessView.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERA);
			tessView.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if(c  instanceof TesseraView) {
					Gui.getCoda().aggiungi(((TesseraView) c).getIndice());
					Gui.getFinestraPartita().getPanelTessereDaAcquistare().panelTessere.removeAll();
					Gui.getFinestraPartita().getPanelTessereDaAcquistare().setVisible(false);
				}
				
			}
		});
			panelTessere.add(tessView);
			cont++;
		}
		setVisible(true);
	}
}
