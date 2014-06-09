package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class CostantiGui {

	// Risoluzione spostamento
	public static int risoluzioneSpostamento = 100;


	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiGui() {
	}

	public static String percorsoImmagini = new String("immagini/");
	
	public static Color coloreAcqua = new Color(34, 145, 243);
	
	// Dimensioni delle immagini disegnate
	public static final Dimension dimensioneMappa;
	static {
		dimensioneMappa = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().height*(0.75)), Toolkit.getDefaultToolkit().getScreenSize().height);
	}
	public static final Dimension dimensionePecora = new Dimension(dimensioneMappa.height/15, dimensioneMappa.height/20);
	public static final Dimension dimensioneMontone = new Dimension(dimensioneMappa.height/15, dimensioneMappa.height/20);
	public static final Dimension dimensioneAgnello = new Dimension(dimensioneMappa.height/15, dimensioneMappa.height/20);
	public static final Dimension dimensioneLupo = new Dimension(dimensioneMappa.height/15, dimensioneMappa.height/20);
	
	// Panel Tessere
	public static final Dimension dimensionePanelTessera = new Dimension(dimensioneMappa.height/6, dimensioneMappa.height/6);
	public static final int dimensioneAngolini = dimensioneMappa.height/(6*5);
	
	//Panel pastori
	public static final Dimension dimensionePannelloGiocatori = new Dimension(200, dimensioneMappa.height/2); 

	// Coordinate delle posizioni delle pecore nei territori (5 per ogni territorio)
	public static final Point[][] coordinate = {};
}
