package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import java.io.InputStream;
import java.io.PrintStream;

public class CostantiCli {

	/**
	 * Costruttore provato per nascondere quello di default
	 */
	private CostantiCli() {
	}
	
	public static final InputStream DEFAULT_INPUT = System.in;
	
	public static PrintStream DEFAULT_OUTPUT = System.out;
	
}
