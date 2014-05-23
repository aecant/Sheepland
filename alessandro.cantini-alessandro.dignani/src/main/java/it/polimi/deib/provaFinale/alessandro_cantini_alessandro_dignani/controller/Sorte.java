package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.util.Random;

public class Sorte {
	private Sorte() {
	}

	public static int lanciaDado() {
		return numeroCasuale (1, 6);
    }
	
	public static boolean lanciaMoneta() {
		return numeroCasuale(0, 1) == 0 ? false : true;
	}
	
	private static int numeroCasuale (int min, int max){
		Random r = new Random();
	
		return r.nextInt(max-min+1) + min;
	}
}
