package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartitaView {
	private JFrame finestra;
	private MappaView mappa;
	private JPanel panelTessere;
	private JPanel panelGiocatoriMosse;

	// temporanei
	private JLabel lblGiocatoriMosse;

	/**
	 * classe che crea la finestra con tutta la grafica del gioco
	 */
	public PartitaView() {		
		
		// imposto il label dei giocatori (temporaneo)
		lblGiocatoriMosse = new JLabel("Giocatori e Mosse");

		// imposto il panel della mappa
		mappa = new MappaView();
		
		// imposto il panel delle tessere
		panelTessere = new PannelloTessere();

		// imposto il panel dei giocatori
		panelGiocatoriMosse = new JPanel();
		panelGiocatoriMosse.setBackground(new Color(34, 145, 243));
		panelGiocatoriMosse.add(lblGiocatoriMosse);

		// imposto la finestra
		finestra = new JFrame("Sheepland - The Videogame");
		finestra.setLayout(new BorderLayout());

		// aggiungo i panel alla finestra
		finestra.add(panelTessere, BorderLayout.WEST);
		finestra.add(mappa, BorderLayout.CENTER);
		finestra.add(panelGiocatoriMosse, BorderLayout.EAST);

		finestra.pack();
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void visualizza() {
		finestra.setVisible(true);
	}

	public static void main(String[] args) {
		PartitaView tabellone = new PartitaView();
		tabellone.visualizza();
	}
}
