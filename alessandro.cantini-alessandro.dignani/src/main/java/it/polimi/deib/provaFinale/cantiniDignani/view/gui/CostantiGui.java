package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public class CostantiGui {

	// Risoluzione spostamento
	public static int NUM_PASSI_SPOSTAMENTO = 100;


	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiGui() {
	}

	public static String PERCORSO_IMMAGINI = new String("immagini/");
	
	public static Color COLORE_ACQUA = new Color(34, 145, 243);
	
	public static final Dimension DIMENSIONE_SCHERMO;
	static {
		DIMENSIONE_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	// Dimensioni delle immagini disegnate
	public static final Dimension DIMENSIONE_MAPPA = new Dimension((int) (DIMENSIONE_SCHERMO.height*(0.75)), DIMENSIONE_SCHERMO.height);
	public static final Dimension DIMENSIONE_PECORA = new Dimension(DIMENSIONE_MAPPA.height/15, DIMENSIONE_MAPPA.height/20);
	public static final Dimension DIMENSIONE_MONTONE = new Dimension(DIMENSIONE_MAPPA.height/15, DIMENSIONE_MAPPA.height/20);
	public static final Dimension DIMENSIONE_AGNELLO = new Dimension(DIMENSIONE_MAPPA.height/15, DIMENSIONE_MAPPA.height/20);
	public static final Dimension DIMENSIONE_LUPO = new Dimension(DIMENSIONE_MAPPA.height/15, DIMENSIONE_MAPPA.height/20);
	
	// Panel Tessere
	public static final Dimension DIMENSIONE_PANEL_TESSERA = new Dimension(DIMENSIONE_MAPPA.height/6, DIMENSIONE_MAPPA.height/6);
	public static final int DIMENSIONE_ANGOLINI = DIMENSIONE_MAPPA.height/(6*5);
	public static final Color COLORE_SFONDO_ANGOLINI = new Color(255, 255, 255, 180);
	
	// Panel giocatori
	public static final Dimension DIMENSIONE_PANEL_GIOCATORE = new Dimension(200, 70);
	public static final Color COLORE_SFONDO_NOME_GIOC = new Color(255, 255, 255, 180);
	public static final Dimension DIMENSIONE_PANEL_SOLDI = new Dimension(50, 50);
	
	// panels laterali mappa
	public static final Dimension DIMENSIONE_PAN_LATERALI_MAPPA = new Dimension((int) (DIMENSIONE_SCHERMO.width-DIMENSIONE_SCHERMO.height*(0.75)-DIMENSIONE_PANEL_GIOCATORE.width-DIMENSIONE_PANEL_TESSERA.width)/2, DIMENSIONE_SCHERMO.height);
	
	// Coordinate delle posizioni delle pecore nei territori (5 per ogni territorio)
	public static final Point[][] COORDINATE = {}; // TODO
	
	// Panel mosse
	public static final Dimension DIMENSIONE_PANEL_MOSSA = new Dimension(150, 80);
	
	// Fonts
	public static final Font FONT_PULSANTI_MOSSE = new Font("Chalkduster", Font.PLAIN, 18);
	public static final Font FONT_NOME_GIOCATORE = new Font("Chalkduster", Font.PLAIN, 16);
	public static final Font FONT_SOLDI = new Font("Farisi", Font.PLAIN, 30);
	public static final Font FONT_TERRENI = new Font("Arial", Font.BOLD, 14);
}
