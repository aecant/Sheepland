package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class CostantiGui {
	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiGui() {
	}
		
	public static String percorsoImmagini = new String("immagini/");
	
	public static Color coloreAcqua = new Color(34, 145, 243);
	
	// Dimensioni delle immagini disegnate
	public static Dimension dimensionePecora = new Dimension(40, 30);
	public static Dimension dimensioneMontone = new Dimension(40, 30);
	public static Dimension dimensioneAgnello = new Dimension(40, 30);
	public static Dimension dimensioneLupo = new Dimension(40, 30);
	public static Dimension dimensioneMappa = new Dimension(500, 700);	
	
	public static int dimensioneAngolini = 20;
	
	// Risoluzione spostamento
	public static int risoluzioneSpostamento = 100;
	
	// Coordinate delle posizioni delle pecore nei territori (5 per ogni territorio)
	public static Point[][] coordinate = {};
}
