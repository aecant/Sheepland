package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mappa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;


public class Main {

	public static void main(String[] args) {
		Territorio[] t =  Mappa.getMappa().getTerritori();
	}

}
