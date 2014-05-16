package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Utilita;

import java.util.ArrayList;

/**
 * Singleton
 */
public class Mappa {
	private static Mappa istanza = null;
	private Territorio[] territori;
	private Strada[] strade;
	
	private Mappa(){
		territori = creaTerritori();
		strade = creaStrade();
	}
	
	/**
	 * Metodo di servizio che crea un array di territori il cui codice è l'indice dell'array 
	 * in @Costanti.MAPPA, il tipo viene letto in @Costanti.TERRITORIO_CODICE
	 * 
	 * @return l'array di territori
	 */
	private Territorio[] creaTerritori() {	
		Territorio[] temp = new Territorio[Costanti.NUM_TERRITORI];
		
		for(int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			temp[i] = new Territorio(i, Costanti.TERRITORIO_CODICE[i]);
		}
		
		return temp;
	}
	
	/**
	 * Metodo di servizio che restituisce un array di strade creato a partire dai territori
	 * @return l'array di strade
	 */
	private Strada[] creaStrade() {
		ArrayList<Strada> temp = new ArrayList<Strada>();
		for(int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for(int j = i + 1; j < Costanti.NUM_TERRITORI; j++) {
				if (sonoConfinanti(territori[i], territori[j]))
					temp.add(new Strada (territori[i], territori[j]));
			}
		}
		
		return temp.toArray(new Strada[temp.size()]);
	}
	

	/** 
	 * Controlla se due territori sono confinanti, controllando se ci il codice di t2 e'
	 * contenuto nella riga di t1 della matrice di transizione @Costanti.MAPPA
	 * @param t1
	 * @param t2
	 * @return true se i territori sono confinanti, false altrimenti
	 */
	public boolean sonoConfinanti(Territorio t1, Territorio t2) {
		return Utilita.contiene(Costanti.MAPPA[t1.getCodice()], t2.getCodice());
	}

	public static Mappa getMappa() {
		if (istanza == null)
			istanza = new Mappa();
		return istanza;
	}

	public Territorio[] getTerritori() {
		return territori;
	}

	public Strada[] getStrade() {
		return strade;
	}
	
	
}
