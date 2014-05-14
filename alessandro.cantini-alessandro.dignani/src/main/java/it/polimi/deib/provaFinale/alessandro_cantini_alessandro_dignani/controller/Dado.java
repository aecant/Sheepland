package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.util.Random;

public class Dado {

	public static int lancia() {
		Random r = new Random();
		return 1 + r.nextInt(5);
    }

}
