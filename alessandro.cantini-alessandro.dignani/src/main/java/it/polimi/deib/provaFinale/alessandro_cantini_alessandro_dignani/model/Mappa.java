package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;
import java.util.List;

public class Mappa {
	private static Mappa istanza = null;
	private List<Territorio> territorio = new ArrayList<Territorio>();
	private List<Strada> strada = new ArrayList<Strada>();
	
	private Mappa(){
		//TODO creazione mappa
	}

	public static Mappa creaMappa() {
		if (istanza == null){
			istanza = new Mappa();
		}
		return istanza;
	}

}
