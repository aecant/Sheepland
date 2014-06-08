package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.Dimension;
import java.io.PrintStream;

public class Costanti {
	/**
	 * Costruttore provato per nascondere quello di default
	 */
	private Costanti() {
	}
	
	public static PrintStream OUTPUT = System.out;
	
	public static String percorsoImmagini = new String("immagini/");
	
	// Dimensioni delle immagini disegnate
	public static Dimension dimensionePecora = new Dimension(40, 30);
	public static Dimension dimensioneMontone = new Dimension(40, 30);
	public static Dimension dimensioneAgnello = new Dimension(40, 30);
	public static Dimension dimensioneLupo = new Dimension(40, 30);
	
	// Risoluzione spostamento
	public static int risoluzioneSpostamento = 100;
	
}
