package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mappa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;


public class Main {

	public static void main(String[] args) {
		Territorio[] territori =  Mappa.getMappa().getTerritori();
		Strada[] strade = Mappa.getMappa().getStrade();
				
		for(Territorio terr : territori) {
			System.out.println(terr);
		}
		
		System.out.println("\n\nStrade:\n");
		
		for(Strada str : strade) {
			System.out.println(str);
		}
	}

}
