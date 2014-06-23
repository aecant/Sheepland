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
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTessereDaVendere extends JPanel {

	private static final long serialVersionUID = -2079063338583718618L;

	private JLabel lblMessaggio = new JLabel();
	private JPanel panelTessere = new JPanel();
	private JButton butSalta = new JButton("Salta");

	protected PanelTessereDaVendere() {
		setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width) / 2, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height) / 2, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.width,CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height);
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
				Gui.getCoda().aggiungi(CostantiController.TERMINATORE_MARKET);
				panelTessere.removeAll();
				setVisible(false);
			}
		});

		add(lblMessaggio, BorderLayout.NORTH);
		add(panelTessere, BorderLayout.CENTER);
		add(butSalta, BorderLayout.SOUTH);

		setVisible(false);
	}

	public void sceltaTipoTessera(List<TipoTerritorio> tessereDisponibili) {
		int cont = 0;
		lblMessaggio.setText("Scegli la tessera da vendere");
		for (TipoTerritorio t : tessereDisponibili) {
			Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + t.name().toLowerCase() + ".jpg")
					.getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
			TesseraView tessView = new TesseraView(img, 0, null, cont);
			impostaTessera(tessView);
			impostaAscoltatore(tessView);
			cont++;
		}
		visualizza(cont);
	}

	

	public void sceltaPrezzo(TipoTerritorio t) {
		butSalta.setEnabled(false);
		for(MouseListener ml : butSalta.getMouseListeners()) {
			butSalta.removeMouseListener(ml);
		}
		lblMessaggio.setText("Scegli il prezzo a cui venderla");
		Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + t.name().toLowerCase() + ".jpg").getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
		for (int i = 1; i <= CostantiController.MAX_PREZZO_MARKET; i++) {
			TesseraView tessView = new TesseraView(img, i, null, i);
			impostaTessera(tessView);
			tessView.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Component c = e.getComponent();
					if (c instanceof TesseraView) {
						clickTessera(c);
						butSalta.setEnabled(true);
						butSalta.addMouseListener(new java.awt.event.MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								Gui.getCoda().aggiungi(CostantiController.TERMINATORE_MARKET);
								panelTessere.removeAll();
								setVisible(false);
							}
						});
					}
				}
			});
		}
		visualizza(CostantiController.MAX_PREZZO_MARKET - 1);
	}

	public void marketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		int cont = 0;
		lblMessaggio.setText("Scegli la tessera da acquistare");
		for (TesseraInVendita tess : tessereDisponibili) {
			Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + tess.getTipo().name().toLowerCase() + ".jpg")
					.getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, Image.SCALE_SMOOTH);
			TesseraView tessView = new TesseraView(img, tess.getPrezzo(), null, cont);
			impostaTessera(tessView);
			impostaAscoltatore(tessView);
			cont++;
		}
		visualizza(cont);
	}

	private void clickTessera(Component c) {
		Gui.getCoda().aggiungi(((TesseraView) c).getIndice());
		panelTessere.removeAll();
		setVisible(false);
	}
	
	private void impostaTessera(TesseraView tessView) {
		tessView.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_TESSERA);
		panelTessere.add(tessView);
	}
	
	private void impostaAscoltatore(TesseraView tessView) {
		tessView.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if (c instanceof TesseraView) {
					clickTessera(c);
				}
			}
		});
	}
	
	private void visualizza(int cont) {
		int larghezza = CostantiGui.DIMENSIONE_PANEL_TESSERA.width * (cont + 1) + 30;
		setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - larghezza) / 2, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height) / 2, larghezza, CostantiGui.DIMENSIONE_PANEL_ACQUISTO_TESSERA.height);
		setVisible(true);
	}
}
