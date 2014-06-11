package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.awt.Color;

public enum ColorePastore {
	ROSSO(Color.RED),
	BLU(Color.BLUE),
	VERDE(Color.GREEN),
	GIALLO(Color.YELLOW);
	
	public final Color coloreView;
	
	private ColorePastore(Color coloreView) {
		this.coloreView = coloreView;
	}
	
	@Override 
	public String toString() {
		return name().toLowerCase();
	}
}
