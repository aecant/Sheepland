package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public class CostantiGui {

	// Risoluzione spostamento
	public static int NUM_PASSI_SPOSTAMENTO = 60;


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
	public static final Dimension DIMENSIONE_PECORA = new Dimension(DIMENSIONE_MAPPA.height/18, DIMENSIONE_MAPPA.height/24);
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
	
	/**
	 * Coordinate delle posizioni delle pecore nei territori (5 per ogni territorio)
	 * [Attenzione] Le coordinate salvate sono relative ad uno schermo con risoluzione 1280x800
	 * Queste coordinate andranno poi moltiplicate per il FATTORE_DI_SCALA
	 * calcolato facendo PIXEL_ALTEZZA_SCHERMO_CORRENTE / 800
	 */
	public static final Point[][] COORDINATE = {
		// Sheepsburg
		{new Point(313, 366), new Point(316, 392), new Point(316, 340), new Point(276, 367), new Point(347, 372)},
		// Montagna
		{new Point(338, 249), new Point(306, 236), new Point(343, 221), new Point(307, 289), new Point(348, 296)},
		{new Point(454, 179), new Point(440, 222), new Point(487, 124), new Point(420, 188), new Point(512, 165)},
		{new Point(546, 232), new Point(516, 272), new Point(499, 236), new Point(537, 205), new Point(559, 264)},
		// Deserto
		{new Point(420, 312), new Point(418, 281), new Point(455, 300), new Point(385, 312), new Point(434, 353)},
		{new Point(524, 364), new Point(518, 408), new Point(477, 366), new Point(499, 313), new Point(551, 324)},
		{new Point(520, 526), new Point(460, 504), new Point(478, 543), new Point(483, 450), new Point(522, 476)},
		// Lago
		{new Point(408, 455), new Point(415, 404), new Point(403, 486), new Point(374, 423), new Point(454, 420)},
		{new Point(386, 594), new Point(410, 550), new Point(397, 637), new Point(404, 688), new Point(450, 608)},
		{new Point(277, 678), new Point(302, 652), new Point(234, 721), new Point(321, 613), new Point(195, 757)},
		// Bosco
		{new Point(318, 510), new Point(290, 526), new Point(330, 554), new Point(306, 449), new Point(348, 491)},
		{new Point(207, 600), new Point(204, 558), new Point(260, 595), new Point(147, 634), new Point(179, 668)},
		{new Point(130, 511), new Point(83, 486), new Point(170, 521), new Point(148, 473), new Point(112, 572)},
		// Pascolo
		{new Point(234, 462), new Point(231, 403), new Point(233, 504), new Point(195, 438), new Point(270, 435)},
		{new Point(151, 364), new Point(143, 306), new Point(145, 402), new Point(97, 324), new Point(93, 429)},
		{new Point(86, 214), new Point(119, 152), new Point(154, 253), new Point(64, 145), new Point(95, 262)},
		// Campo
		{new Point(230, 310), new Point(243, 265), new Point(227, 344), new Point(206, 278), new Point(266, 291)},
		{new Point(225, 179), new Point(216, 217), new Point(170, 169), new Point(287, 168), new Point(173, 117)},
		{new Point(380, 117), new Point(380, 168), new Point(433, 90), new Point(340, 125), new Point(377, 58)}
	}; // TODO inserire le coordinate
	
	// Panel mosse
	public static final Dimension DIMENSIONE_PANEL_MOSSA = new Dimension(150, 80);
	
	// Fonts
	public static final Font FONT_PULSANTI_MOSSE = new Font("Chalkduster", Font.PLAIN, 18);
	public static final Font FONT_NOME_GIOCATORE = new Font("Chalkduster", Font.PLAIN, 16);
	public static final Font FONT_SOLDI = new Font("Farisi", Font.PLAIN, 30);
	public static final Font FONT_TERRENI = new Font("Arial", Font.BOLD, 14);
}
