package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PartitaView {
	private JFrame finestra;
	private MappaView mappa;

	private JPanel panelTessere;
	private JPanel panelGiocatoriMosse;
	private JPanel panelGiocatori;

	private DatiPartita datiPartita;

	/**
	 * classe che crea la finestra con tutta la grafica del gioco
	 */
	public PartitaView(DatiPartita datiPartita) {
		this.datiPartita = datiPartita;

		// imposto il panel della mappa
		mappa = new MappaView();

		// imposto il panel delle tessere
		panelTessere = new PannelloTessere();

		// imposto il panel dei giocatori
		panelGiocatori = new PannelloGiocatori(this.datiPartita.getGiocatori());
		panelGiocatoriMosse = new JPanel(new BorderLayout());
		panelGiocatori.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelGiocatoriMosse.add(panelGiocatori, BorderLayout.NORTH);
		panelGiocatoriMosse.setBackground(CostantiGui.coloreAcqua);

		// imposto la finestra
		finestra = new JFrame("Sheepland - The Videogame");
		finestra.setLayout(new BorderLayout());

		// aggiungo i panel alla finestra
		finestra.add(panelTessere, BorderLayout.WEST);
		finestra.add(mappa, BorderLayout.CENTER);
		finestra.add(panelGiocatoriMosse, BorderLayout.EAST);

		finestra.pack();
		// finestra.setResizable(false);
		finestra.setLocation(300, 150);
		finestra.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void visualizza() {
		finestra.setVisible(true);
	}

	public MappaView getMappa() {
		return this.mappa;
	}
}
