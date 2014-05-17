package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.List;

public class PedinaSuStrada {
	private Strada strada;

	public PedinaSuStrada(Strada strada) {
		this.strada = strada;
	}
	
	/**
	 * Data una strada ed una lista di pedine su strada controlla se la strada è occupata da una di quelle pedine
	 * 
	 * @param lista
	 * @param strada
	 * @return
	 */
	public static boolean stradaOccupata(List<PedinaSuStrada> lista, Strada strada) {
		for(PedinaSuStrada p : lista)
			if(p.getStrada().equals(strada))
				return true;
		return false;
	}

	public Strada getStrada() {
		return this.strada;
	}

	public void setStrada(Strada strada) {
		this.strada = strada;
	}

}
