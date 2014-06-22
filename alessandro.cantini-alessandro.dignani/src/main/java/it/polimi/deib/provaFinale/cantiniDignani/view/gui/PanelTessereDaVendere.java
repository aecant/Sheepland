package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTessereDaVendere extends JPanel {

	private static final long serialVersionUID = -2079063338583718618L;

	private JLabel lblMessaggio = new JLabel("Scegli la tessera da vendere");
	private JPanel panelTessere = new JPanel();
	private JButton butSalta = new JButton("Salta");

	protected PanelTessereDaVendere() {
		setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width) / 2,
				(CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height) / 2, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width,
				CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height);
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

		// Imposto il bottone salta
		butSalta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if (c instanceof TesseraView) {
					Gui.getCoda().aggiungi(CostantiController.TERMINATORE_MARKET);
					panelTessere.removeAll();
					setVisible(false);
				}
			}
		});
		
		add(lblMessaggio, BorderLayout.NORTH);
		add(panelTessere, BorderLayout.CENTER);
		add(butSalta, BorderLayout.SOUTH);

		setVisible(false);
	}

	public void sceltaTipoTessera(List<TipoTerritorio> tessereDisponibili) {
		int cont = 0;
		for (TipoTerritorio t : tessereDisponibili) {
			Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + t.name().toLowerCase() + ".jpg")
					.getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
			TesseraView tessView = new TesseraView(img, 0, null, cont);
			tessView.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERA);
			tessView.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Component c = e.getComponent();
					if (c instanceof TesseraView) {
						Gui.getCoda().aggiungi(((TesseraView) c).getIndice());
						panelTessere.removeAll();
						setVisible(false);
					}
				}
			});
			panelTessere.add(tessView);
			cont++;
		}
		setVisible(true);
	}

	public void sceltaPrezzo(TipoTerritorio t) {
		butSalta.setEnabled(false);

		Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + t.name().toLowerCase() + ".jpg")
				.getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
		for (int i = 1; i < 5; i++) {
			TesseraView tessView = new TesseraView(img, i, null, i);
			tessView.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERA);
			tessView.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Component c = e.getComponent();
					if (c instanceof TesseraView) {
						Gui.getCoda().aggiungi(((TesseraView) c).getIndice());
						panelTessere.removeAll();
						setVisible(false);
						butSalta.setEnabled(true);
					}
				}
			});
			panelTessere.add(tessView);
		}
	}

	public void marketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		int cont = 0;
		for (TesseraInVendita tess : tessereDisponibili) {
			Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + tess.getTipo().name().toLowerCase() + ".jpg")
					.getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
			TesseraView tessView = new TesseraView(img, tess.getPrezzo(), null, cont);
			tessView.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERA);
			tessView.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Component c = e.getComponent();
					if (c instanceof TesseraView) {
						Gui.getCoda().aggiungi(((TesseraView) c).getIndice());
						panelTessere.removeAll();
						setVisible(false);
					}
				}
			});
			panelTessere.add(tessView);
			cont++;
		}
		setVisible(true);
	}
}
