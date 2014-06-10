package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PartitaView {
	private JFrame finestra;
	
	private JPanel panelMappa;
	private JPanel panelMappaSinistra;
	private JPanel panelMappaDestra;
	private MappaView mappa;

	private JPanel panelTessere;
	
	private JPanel panelGiocatoriMosse;
	private JPanel panelGiocatori;
	private JPanel panelMosse;

	private DatiPartita datiPartita;

	/**
	 * classe che crea la finestra con tutta la grafica del gioco
	 */
	public PartitaView(DatiPartita datiPartita) {
		this.datiPartita = datiPartita;

		// imposto il panel della mappa
		panelMappa = new JPanel(new BorderLayout());
		panelMappaSinistra = new JPanel();
		panelMappaSinistra.setPreferredSize(CostantiGui.dimensionePannelliLateraliMappa);
		panelMappaSinistra.setBackground(CostantiGui.coloreAcqua);
		
		panelMappaDestra = new JPanel();
		panelMappaDestra.setPreferredSize(CostantiGui.dimensionePannelliLateraliMappa);
		panelMappaDestra.setBackground(CostantiGui.coloreAcqua);
		
		mappa = new MappaView();
		panelMappa.add(mappa, BorderLayout.CENTER);
		panelMappa.add(panelMappaSinistra, BorderLayout.WEST);
		panelMappa.add(panelMappaDestra, BorderLayout.EAST);

		// imposto il panel delle tessere
		panelTessere = new PannelloTessere();

		// imposto il panel dei giocatori
		panelGiocatori = new PannelloGiocatori(this.datiPartita.getGiocatori());
		
		// imposto il panel delle mosse
		panelMosse = new PannelloMosse();
		
		// imposto il panel che contiene i panel delle mosse e dei giocatori
		panelGiocatoriMosse = new JPanel(new BorderLayout());
		panelGiocatoriMosse.add(panelGiocatori, BorderLayout.NORTH);
		panelGiocatoriMosse.add(panelMosse, BorderLayout.SOUTH);
		panelGiocatoriMosse.setBackground(CostantiGui.coloreAcqua);

		// imposto la finestra
		finestra = new JFrame("Sheepland - The Videogame");
		finestra.setLayout(new BorderLayout());

		// aggiungo i panel alla finestra
		finestra.add(panelTessere, BorderLayout.WEST);
		finestra.add(panelMappa, BorderLayout.CENTER);
		finestra.add(panelGiocatoriMosse, BorderLayout.EAST);

		finestra.pack();
		finestra.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		java.awt.GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice cc = ge.getDefaultScreenDevice();
		cc.setFullScreenWindow(finestra);
	}

	public void visualizza() {
		finestra.setVisible(true);
	}

	public MappaView getMappa() {
		return this.mappa;
	}
}
