package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.awt.Color;

public enum ColorePastore {
	ROSSO(new Color(217, 0, 40)),
	BLU(new Color(0, 61, 217)),
	VERDE(new Color(0, 217, 36)),
	GIALLO(new Color(217, 209, 0));
	
	public final Color coloreView;
	
	private ColorePastore(Color coloreView) {
		this.coloreView = coloreView;
	}
	
	@Override 
	public String toString() {
		return name().toLowerCase();
	}
}
