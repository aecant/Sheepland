package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PartitaView extends JFrame {
	private static final long serialVersionUID = 7676869943311337795L;

	private MappaView mappa;
	private PannelloTessere panelTessere;
	private PannelloGiocatori panelGiocatori;
	private PannelloMosse panelMosse;
	private PanelMessaggi panelMessaggi;
	private PanelTessereDaAcquistare panelTessereDaAcquistare;
	private PanelTessereDaVendere panelTessereDaVendere;

	/**
	 * Classe che crea la finestra con tutta la grafica del gioco
	 */
	public PartitaView(DatiPartita datiPartita, boolean riconnessione) {
		JPanel panelGiocatoriMosse;
		JPanel panelMappa;
		JPanel panelMappaSinistra;
		JPanel panelMappaDestra;
		JPanel contenitore;
		JLayeredPane contenitoreLayer;

		setTitle("Sheepland - " + MainClient.getNome());
		setLayout(new BorderLayout());

		// imposto il panel della mappa
		panelMappa = new JPanel(new BorderLayout());
		panelMappaSinistra = new JPanel();
		panelMappaSinistra.setPreferredSize(CostantiGui.DIMENSIONE_PAN_LATERALI_MAPPA);
		panelMappaSinistra.setBackground(CostantiGui.COLORE_ACQUA);

		panelMappaDestra = new JPanel();
		panelMappaDestra.setPreferredSize(CostantiGui.DIMENSIONE_PAN_LATERALI_MAPPA);
		panelMappaDestra.setBackground(CostantiGui.COLORE_ACQUA);

		mappa = new MappaView(riconnessione);
		panelMappa.add(mappa, BorderLayout.CENTER);
		panelMappa.add(panelMappaSinistra, BorderLayout.WEST);
		panelMappa.add(panelMappaDestra, BorderLayout.EAST);

		// imposto il layeredPane
		contenitoreLayer = new JLayeredPane();
		
		// imposto il panel delle tessere
		panelTessere = new PannelloTessere(datiPartita.getTessereInCima(), datiPartita.getGiocatore(MainClient.getNome()).numeroTesserePerTipo());

		// imposto il panel dei giocatori
		panelGiocatori = new PannelloGiocatori(datiPartita.getGiocatori());

		// imposto il panel delle mosse
		panelMosse = new PannelloMosse();

		// imposto il panel che contiene i panel delle mosse e dei giocatori
		panelGiocatoriMosse = new JPanel(new BorderLayout());
		panelGiocatoriMosse.add(panelGiocatori, BorderLayout.NORTH);
		panelGiocatoriMosse.add(panelMosse, BorderLayout.SOUTH);
		panelGiocatoriMosse.setBackground(CostantiGui.COLORE_ACQUA);

		// imposto la finestra
		setVisible(false);

		// imposto il panel contenitore
		contenitore = new JPanel(new BorderLayout());

		// aggiungo i panel al contenitore
		contenitore.add(panelTessere, BorderLayout.WEST);
		contenitore.add(panelMappa, BorderLayout.CENTER);
		contenitore.add(panelGiocatoriMosse, BorderLayout.EAST);
		contenitore.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_SCHERMO));

		// imposto il panelMessaggi
		panelMessaggi = new PanelMessaggi();

		// imposto il panelTessereDaAcquistare
		panelTessereDaAcquistare = new PanelTessereDaAcquistare();

		// imposto il panelTessereDaVendere
		panelTessereDaVendere = new PanelTessereDaVendere();

		// aggiungo il contenitore alla finestra
		contenitoreLayer.add(panelMessaggi, new Integer(7));
		contenitoreLayer.add(panelTessereDaAcquistare, new Integer(5));
		contenitoreLayer.add(panelTessereDaVendere, new Integer(6));
		contenitoreLayer.add(contenitore, new Integer(0));
		
		add(contenitoreLayer, BorderLayout.CENTER);

		pack();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (CostantiGui.SCHERMO_INTERO) {
			java.awt.GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice cc = ge.getDefaultScreenDevice();
			cc.setFullScreenWindow(this);
		}
	}

	public void visualizza() {
		setVisible(true);
	}

	public MappaView getMappa() {
		return this.mappa;
	}

	public PanelMessaggi getPanelMessaggi() {
		return this.panelMessaggi;
	}

	public PannelloTessere getPanelTessere() {
		return panelTessere;
	}

	public PannelloGiocatori getPanelGiocatori() {
		return panelGiocatori;
	}

	public PannelloMosse getPanelMosse() {
		return panelMosse;
	}

	public PanelTessereDaAcquistare getPanelTessereDaAcquistare() {
		return panelTessereDaAcquistare;
	}

	public PanelTessereDaVendere getPanelTessereDaVendere() {
		return panelTessereDaVendere;
	}
}
