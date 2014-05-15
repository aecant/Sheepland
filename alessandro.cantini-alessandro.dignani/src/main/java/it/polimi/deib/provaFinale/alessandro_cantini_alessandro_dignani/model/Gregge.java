package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Gregge {
	private static Gregge istanza = null;
	ArrayList<Pecora> pecore;
	
	private Gregge() {
		pecore = new ArrayList<Pecora>();
	}
	
	public static Gregge getGregge() {
		if (istanza == null)
			istanza = new Gregge();
		return istanza;
	}

	public static void trasformaAgnelloInPecora(Agnello agnello) {
		// TODO Auto-generated method stub
		
	}
}
