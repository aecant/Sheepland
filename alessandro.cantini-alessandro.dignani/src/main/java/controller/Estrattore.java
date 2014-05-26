package controller;

import model.Mappa;
import model.Partita;
import model.Territorio;
import model.Tessera;

/**
 * Classe che fornisce metodi statici per effettuare query e ottenere dati dal
 * model
 */
public class Estrattore {

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private Estrattore() {
	}
	
	
	public static Tessera[] tessereConfinanti(Partita partita, int strada) {
		Tessera[] tessere = new Tessera[2];

		Territorio t1, t2;
		t1 = Mappa.getMappa().getStrade()[strada].getTerritorio1();
		t2 = Mappa.getMappa().getStrade()[strada].getTerritorio2();

		tessere[0] = partita.getMazzo().leggiTesseraInCima(t1.getTipo());
		tessere[1] = partita.getMazzo().leggiTesseraInCima(t2.getTipo());

		return tessere;
	}
}
