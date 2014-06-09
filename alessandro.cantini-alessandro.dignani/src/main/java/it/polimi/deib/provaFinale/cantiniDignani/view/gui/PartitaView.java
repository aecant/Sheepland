package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PartitaView {
	private JFrame finestra;
	private MappaView mappa;

	private JPanel panelTessere;
	private JPanel panelGiocatoriMosse;

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
		panelGiocatoriMosse = new PannelloGiocatori(this.datiPartita.getGiocatori());

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

	public static void main(String[] args) {
		DatiPartita dati = new DatiPartita();
		PartitaView tabellone = new PartitaView(null);
		tabellone.visualizza();

		while (true) {
			tabellone.getMappa().getPec().muoviPecora(new Point((int) Sorte.numeroCasuale(0, 415), (int) Sorte.numeroCasuale(0, 600)));
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
