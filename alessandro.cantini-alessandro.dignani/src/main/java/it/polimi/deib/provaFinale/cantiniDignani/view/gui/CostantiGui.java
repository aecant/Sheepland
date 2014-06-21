package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public class CostantiGui {

	public static final boolean SCHERMO_INTERO = false;
	
	// Risoluzione spostamento
	public static int NUM_PASSI_SPOSTAMENTO = 60;

	public static String PERCORSO_IMMAGINI = new String("immagini/");

	// Colori
	public static Color COLORE_ACQUA = new Color(34, 145, 243);

	public static final Dimension DIMENSIONE_SCHERMO;
	static {
		DIMENSIONE_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize();
		if(!SCHERMO_INTERO) {
			DIMENSIONE_SCHERMO.height -= 40;
		}
	}
	
	public static final double FATTORE_DI_SCALA = (double) DIMENSIONE_SCHERMO.height/800;

	// Dimensioni delle immagini disegnate
	public static final Dimension DIMENSIONE_MAPPA = new Dimension((int) (DIMENSIONE_SCHERMO.height * (0.75)), DIMENSIONE_SCHERMO.height);
	public static final Dimension DIMENSIONE_PECORA = new Dimension(DIMENSIONE_MAPPA.height / 18, DIMENSIONE_MAPPA.height / 24);
	public static final Dimension DIMENSIONE_MONTONE = new Dimension(DIMENSIONE_MAPPA.height / 18, DIMENSIONE_MAPPA.height / 24);
	public static final Dimension DIMENSIONE_AGNELLO = new Dimension(DIMENSIONE_MAPPA.height / 21, DIMENSIONE_MAPPA.height / 28);
	public static final Dimension DIMENSIONE_LUPO = new Dimension(DIMENSIONE_MAPPA.height / 12, DIMENSIONE_MAPPA.height / 16);
	public static final Dimension DIMENSIONE_PASTORE = new Dimension(DIMENSIONE_MAPPA.height / 24, DIMENSIONE_MAPPA.height / 24);

	// Panel Tessere
	public static final Dimension DIMENSIONE_PANEL_TESSERA = new Dimension(DIMENSIONE_MAPPA.height / TipoTerritorio.valoriTessere().length, DIMENSIONE_MAPPA.height / TipoTerritorio.valoriTessere().length);
	public static final int DIMENSIONE_ANGOLINI = DIMENSIONE_MAPPA.height / (TipoTerritorio.valoriTessere().length * 5);
	public static final Color COLORE_SFONDO_ANGOLINI = new Color(255, 255, 255, 180);

	// Panel giocatori
	public static final Dimension DIMENSIONE_PANEL_GIOCATORE = new Dimension(200, 70);
	public static final Color COLORE_SFONDO_NOME_GIOC = new Color(255, 255, 255, 180);
	public static final Dimension DIMENSIONE_PANEL_SOLDI = new Dimension(50, 50);
	
	// Panel messaggi
	public static final Dimension DIMENSIONE_PANEL_MESSAGGI = new Dimension(DIMENSIONE_SCHERMO.width, DIMENSIONE_SCHERMO.height / 3);
	public static final Dimension DIMENSIONE_IMG_MOTIVO_LANCIO = new Dimension(150, 150);
	public static final Dimension DIMENSIONE_IMG_DADO = new Dimension(50, 50);
	public static final Dimension DIMENSIONE_PANEL_LANCIO_DADO = new Dimension(DIMENSIONE_IMG_MOTIVO_LANCIO.width + 10, DIMENSIONE_IMG_MOTIVO_LANCIO.height + DIMENSIONE_IMG_DADO.height + 15); // TODO da modificare!
	public static final int TEMPO_VISUALIZZAZIONE_MESSAGGIO = 3000;
	public static final Font FONT_MESSAGGI = new Font("Arial", Font.BOLD, 40);
	public static final Color COLORE_SFONDO_MESSAGGI = new Color(252, 189, 73, 200);
	public static final Color COLORE_TESTO_MESSAGGI = new Color(80, 80, 80);
	public static final String PERCORSO_IMMAGINI_DADO = PERCORSO_IMMAGINI + "dado/";
	public static final Color COLORE_SFONDO_LANCIO_DADO = new Color(186, 227, 248);
	
	// Panel scelta tessera da acquistare
	public static final Dimension DIMENSIONE_PANEL_ACQUISTO_TESSERA = new Dimension(300, 200);


	// panels laterali mappa
	public static final Dimension DIMENSIONE_PAN_LATERALI_MAPPA = new Dimension(
			(int) (DIMENSIONE_SCHERMO.width - DIMENSIONE_SCHERMO.height * (0.75) - DIMENSIONE_PANEL_GIOCATORE.width - DIMENSIONE_PANEL_TESSERA.width) / 2, DIMENSIONE_SCHERMO.height);

	/**
	 * Coordinate delle posizioni delle pecore nei territori (5 per ogni
	 * territorio) [Attenzione] Le coordinate salvate sono relative ad uno
	 * schermo con risoluzione 1280x800 Queste coordinate andranno poi
	 * moltiplicate per il FATTORE_DI_SCALA calcolato facendo
	 * PIXEL_ALTEZZA_SCHERMO_CORRENTE / 800
	 */
	public static final Point[][] COORDINATE_TERRITORI = {
			// Sheepsburg
			{ new Point(316, 340), new Point(316, 392), new Point(313, 366), new Point(276, 367), new Point(347, 372) },
			// Montagna
			{ new Point(338, 249), new Point(306, 236), new Point(343, 221), new Point(307, 289), new Point(348, 289) },
			{ new Point(459, 173), new Point(440, 222), new Point(487, 124), new Point(420, 188), new Point(512, 165) },
			{ new Point(546, 232), new Point(516, 272), new Point(499, 236), new Point(537, 205), new Point(559, 264) },
			// Deserto
			{ new Point(420, 312), new Point(418, 281), new Point(455, 300), new Point(385, 312), new Point(434, 353) },
			{ new Point(524, 364), new Point(518, 408), new Point(477, 366), new Point(499, 313), new Point(551, 324) },
			{ new Point(520, 526), new Point(469, 502), new Point(478, 543), new Point(483, 450), new Point(522, 476) },
			// Lago
			{ new Point(408, 455), new Point(415, 404), new Point(403, 486), new Point(374, 423), new Point(454, 420) },
			{ new Point(386, 594), new Point(410, 550), new Point(397, 637), new Point(404, 688), new Point(450, 608) },
			{ new Point(277, 685), new Point(302, 652), new Point(239, 715), new Point(321, 613), new Point(200, 750) },
			// Bosco
			{ new Point(318, 510), new Point(290, 526), new Point(330, 554), new Point(306, 449), new Point(348, 491) },
			{ new Point(207, 610), new Point(215, 558), new Point(260, 595), new Point(147, 634), new Point(179, 668) },
			{ new Point(130, 511), new Point(83, 486), new Point(170, 521), new Point(148, 473), new Point(112, 572) },
			// Pascolo
			{ new Point(234, 462), new Point(231, 403), new Point(233, 504), new Point(200, 434), new Point(270, 435) },
			{ new Point(151, 364), new Point(143, 306), new Point(145, 402), new Point(97, 324), new Point(93, 429) },
			{ new Point(86, 205), new Point(119, 152), new Point(154, 246), new Point(64, 145), new Point(105, 250) },
			// Campo
			{ new Point(230, 310), new Point(243, 265), new Point(227, 344), new Point(206, 278), new Point(266, 291) },
			{ new Point(225, 179), new Point(216, 217), new Point(170, 169), new Point(287, 168), new Point(173, 117) },
			{ new Point(380, 117), new Point(380, 168), new Point(433, 90), new Point(340, 125), new Point(377, 58) } };
	
	
	/**
	 * Coordinate delle posizioni delle strade
	 * [Attenzione] Le coordinate salvate sono relative ad uno
	 * schermo con risoluzione 1280x800 Queste coordinate andranno poi
	 * moltiplicate per il FATTORE_DI_SCALA calcolato facendo
	 * PIXEL_ALTEZZA_SCHERMO_CORRENTE / 800
	 */
	public static final Point[] COORDINATE_STRADE = {
		new Point(318, 311),
		new Point(363, 338),
		new Point(369, 395),
		new Point(325, 424),
		new Point(273, 397),
		new Point(268, 340),
		new Point(397, 227),
		new Point(382, 282),
		new Point(290, 272),
		new Point(306, 212),
		new Point(359, 198),
		new Point(503, 202),
		new Point(434, 255),
		new Point(422, 142),
		new Point(480, 274),
		new Point(540, 292),
		new Point(473, 335),
		new Point(409, 372),
		new Point(525, 441),
		new Point(473, 394),
		new Point(453, 470),
		new Point(458, 571),
		new Point(404, 517),
		new Point(369, 463),
		new Point(368, 637),
		new Point(367, 547),
		new Point(323, 581),
		new Point(233, 664),
		new Point(279, 554),
		new Point(284, 476),
		new Point(153, 601),
		new Point(235, 530),
		new Point(195, 478),
		new Point(146, 440),
		new Point(191, 402),
		new Point(229, 372),
		new Point(102, 292),
		new Point(191, 326),
		new Point(192, 259),
		new Point(154, 202),
		new Point(249, 236),
		new Point(315, 134)
	};

	// Panel mosse
	public static final Dimension DIMENSIONE_PANEL_MOSSA = new Dimension(DIMENSIONE_PANEL_GIOCATORE.width, 80);
	
	// Segnalini Strade
	public static final Dimension DIMENSIONE_SEGNALINO_STRADA = new Dimension(DIMENSIONE_MAPPA.height / 20, DIMENSIONE_MAPPA.height / 20);

	// Fonts
	public static final Font FONT_PULSANTI_MOSSE = new Font("Chalkduster", Font.PLAIN, 18);
	public static final Font FONT_NOME_GIOCATORE = new Font("Trajan Pro 3", Font.BOLD, 16);
	public static final Font FONT_SOLDI = new Font("Farisi", Font.PLAIN, 30);
	public static final Font FONT_CONTATORI_ANIMALI = new Font("Farisi", Font.PLAIN, 20);
	public static final Font FONT_ANGOLINI = new Font("Arial", Font.BOLD, 14);

	public static final int NUM_FRAME_ANIM_MESS = 50;

	public static final Color COLORE_TESTO_ANGOLINI = Color.DARK_GRAY;

	public static final Dimension DIMENSIONE_MONETA_TESSERA = new Dimension(20, 20);

	public static final Dimension DIMENSIONE_ASCOLTATORE_ANIMALE = DIMENSIONE_PECORA;
	public static final Dimension DIMENSIONE_ASCOLTATORE_TERRITORIO = new Dimension(DIMENSIONE_MAPPA.height / 8, DIMENSIONE_MAPPA.height / 12);

	public static final Color COLORE_TESSERA_FINITA = new Color(200, 20, 20, 100);

	public static final Font FONT_MESSAGGIO_ACQUISTO_TESSERA = new Font("Arial", Font.BOLD, 20);

	public static final Dimension DIMENSIONE_PANEL_TESSERE_ACQUISTO = new Dimension(205, 110);;



	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiGui() {
	}
}
