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

	private Mappa() {
		territori = creaTerritori();
		strade = creaStrade();
	}

	/**
	 * Metodo di servizio che crea un array di territori il cui codice è
	 * l'indice dell'array in @Costanti.MAPPA, il tipo viene letto in
	 * @Costanti.TERRITORIO_CODICE
	 * 
	 * @return l'array di territori
	 */
	private Territorio[] creaTerritori() {
		Territorio[] temp = new Territorio[Costanti.NUM_TERRITORI];

		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			temp[i] = new Territorio(i, Costanti.TERRITORIO_CODICE[i]);
		}

		return temp;
	}

	/**
	 * Metodo di servizio che restituisce un array di strade creato a partire
	 * dai territori
	 * 
	 * @return l'array di strade
	 */
	private Strada[] creaStrade() {
		ArrayList<Strada> temp = new ArrayList<Strada>();
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = i + 1; j < Costanti.NUM_TERRITORI; j++) {
				if (sonoConfinanti(territori[i], territori[j]))
					temp.add(new Strada(territori[i], territori[j]));
			}
		}

		return temp.toArray(new Strada[temp.size()]);
	}

	/**
	 * Controlla se due territori sono confinanti, controllando se ci il codice
	 * di t2 e' contenuto nella riga di t1 della matrice di transizione
	 * Costanti.MAPPA
	 * 
	 * @param t1
	 * @param t2
	 * @return true se i territori sono confinanti, false altrimenti
	 */
	public boolean sonoConfinanti(Territorio t1, Territorio t2) throws IllegalArgumentException {
		if (t1.equals(t2))
			throw new IllegalArgumentException("I territori devono essere diversi");
		return Utilita.contiene(Costanti.MAPPA[t1.getCodice()], t2.getCodice());
	}
	
	/**
	 * Date due strade, controlla se sono contigue
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean sonoContigue(Strada s1, Strada s2) {
		Territorio t1, t2;
		Territorio terrInComune = territorioInComune(s1,s2);
		if(terrInComune != null) {
			t1 = territorioOpposto(terrInComune, s1);
			t2 = territorioOpposto(terrInComune, s2);
			return sonoConfinanti(t1, t2);
		}
		
		return false;
	}
	
	/**
	 * Dati un territorio e una strada che lo delimita, restituisce il territorio opposto
	 * a quella strada
	 * @param t
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Territorio territorioOpposto(Territorio t, Strada s) throws IllegalArgumentException{
		if(t.equals(s.getTerritorio1()))
			return s.getTerritorio2();
		if(t.equals(s.getTerritorio2()))
			return s.getTerritorio1();
		
		throw new IllegalArgumentException("Il territorio e la strada devono essere vicini");	
	}
	
	
	/**
	 * Date due strade, restituisce il territorio in comune, null se non ce l'hanno
	 * @param s1
	 * @param s2
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Territorio territorioInComune(Strada s1, Strada s2) throws IllegalArgumentException{
		if(s1.equals(s2))
			throw new IllegalArgumentException("Le strade devono essere diverse");
		if(s1.getTerritorio1().equals(s2.getTerritorio1()) || s1.getTerritorio1().equals(s2.getTerritorio2()))
			return s1.getTerritorio1();
		if(s1.getTerritorio2().equals(s2.getTerritorio1()) || s1.getTerritorio2().equals(s2.getTerritorio2()))
			return s1.getTerritorio2();
		
		return null;
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
