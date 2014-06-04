package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MappaView {
	private Image imgMappa;
	private JFrame finestra;
	private BackgroundPanel mappa;
	private JPanel panelTessere;
	private JPanel panelGiocatori;
	private JPanel panelMosse;
	
	//temporanei
	//private JLabel lblTessere;
	private JLabel lblGiocatori;
	private JLabel lblMosse;
	
	/**
	 * classe che crea la finestra con tutta la grafica del gioco
	 */
	public MappaView() {
		//carico l'immagine della mappa
		imgMappa = Toolkit.getDefaultToolkit().getImage("src/images/mappa.jpg");
		imgMappa = imgMappa.getScaledInstance(500, 700, 0);
		
		//carico l'immagine 
		
		//imposto il label delle tessere (temporaneo)
		// porcata per far vedere il panel delle tessere
		//lblTessere = new JLabel("Tessere........mmmm");
		
		//imposto il label dei giocatori (temporaneo)
		lblGiocatori = new JLabel("Giocatori...");
		
		//imposto il label delle mosse (temporaneo)
		lblMosse = new JLabel("Mosse...");
		
		
		//imposto il panel della mappa
		mappa = new BackgroundPanel(imgMappa);
		
		//imposto il panel delle tessere
		panelTessere = new PannelloTessere();
		
		//imposto il panel dei giocatori
		panelGiocatori = new JPanel();
		panelGiocatori.setSize(100, 600);
		panelGiocatori.add(lblGiocatori);
		
		//imposto il panel delle mosse
		panelMosse = new JPanel();
		panelMosse.setSize(600, 100);
		panelMosse.add(lblMosse);
		
		
		
		//imposto la finestra
		finestra = new JFrame("Sheepland - The Videogame");
		finestra.setLayout(new BorderLayout());
		
		//aggiungo i panel alla finestra
		finestra.add(panelTessere, BorderLayout.WEST);
		finestra.add(mappa, BorderLayout.CENTER);
		finestra.add(panelGiocatori, BorderLayout.EAST);
		finestra.add(panelMosse, BorderLayout.SOUTH);
		
		finestra.pack();
		finestra.setSize(700, 700);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void visualizza() {
		finestra.setVisible(true);
	}

	public static void main(String[] args) {
		MappaView tabellone = new MappaView();
		tabellone.visualizza();
	}
}
