package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
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

	private JPanel contenitore;
	private JPanel panelGiocatoriMosse;
	private JPanel panelGiocatori;
	private JPanel panelMosse;

	private JPanel panelMessaggi;

	private DatiPartita datiPartita;
	
	String io;

	/**
	 * classe che crea la finestra con tutta la grafica del gioco
	 */
	public PartitaView(DatiPartita datiPartita) {
		this.datiPartita = datiPartita;

		// imposto il panel della mappa
		panelMappa = new JPanel(new BorderLayout());
		panelMappaSinistra = new JPanel();
		panelMappaSinistra.setPreferredSize(CostantiGui.DIMENSIONE_PAN_LATERALI_MAPPA);
		panelMappaSinistra.setBackground(CostantiGui.COLORE_ACQUA);

		panelMappaDestra = new JPanel();
		panelMappaDestra.setPreferredSize(CostantiGui.DIMENSIONE_PAN_LATERALI_MAPPA);
		panelMappaDestra.setBackground(CostantiGui.COLORE_ACQUA);

		mappa = new MappaView();
		panelMappa.add(mappa, BorderLayout.CENTER);
		panelMappa.add(panelMappaSinistra, BorderLayout.WEST);
		panelMappa.add(panelMappaDestra, BorderLayout.EAST);
		
		// imposto il panel delle tessere
		panelTessere = new PannelloTessere(this.datiPartita.getTessereInCima(), datiPartita.getGiocatore(Gui.getNome()).numeroTesserePerTipo());

		// imposto il panel dei giocatori
		panelGiocatori = new PannelloGiocatori(this.datiPartita.getGiocatori());

		// imposto il panel delle mosse
		panelMosse = new PannelloMosse();

		// imposto il panel che contiene i panel delle mosse e dei giocatori
		panelGiocatoriMosse = new JPanel(new BorderLayout());
		panelGiocatoriMosse.add(panelGiocatori, BorderLayout.NORTH);
		panelGiocatoriMosse.add(panelMosse, BorderLayout.SOUTH);
		panelGiocatoriMosse.setBackground(CostantiGui.COLORE_ACQUA);

		// imposto la finestra
		finestra = new JFrame("Sheepland - The Videogame");
		finestra.setLayout(null);
		finestra.setVisible(false);

		// imposto il panel contenitore
		contenitore = new JPanel(new BorderLayout());

		// aggiungo i panel al contenitore
		contenitore.add(panelTessere, BorderLayout.WEST);
		contenitore.add(panelMappa, BorderLayout.CENTER);
		contenitore.add(panelGiocatoriMosse, BorderLayout.EAST);
		contenitore.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_SCHERMO));
		
		// imposto il panelMessaggi
		panelMessaggi = new PanelMessaggi();
		

		// aggiungo il contenitore alla finestra
		finestra.add(panelMessaggi);
		finestra.add(contenitore);

		finestra.pack();
		finestra.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (Costanti.SCHERMO_INTERO) {
			java.awt.GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice cc = ge.getDefaultScreenDevice();
			cc.setFullScreenWindow(finestra);
		}
	}

	public void visualizza() {
		finestra.setVisible(true);
	}

	public MappaView getMappa() {
		return this.mappa;
	}
	
	public PanelMessaggi getPanelMessaggi() {
		return (PanelMessaggi) this.panelMessaggi;
	}
}
