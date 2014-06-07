package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.util.ArrayList;

/**
 * Singleton che gestisce la mappa di Sheepland
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
	 * Restituisce il territorio che si ottiene lanciando un dado
	 * 
	 * @param t
	 *            il territorio di partenza
	 * @param dado
	 *            il risultato del lancio del dado
	 * @return il territorio di destinazione
	 */
	public Territorio transizione(Territorio t, int dado) {
		int indice = Costanti.MAPPA[t.getCodice()][dado - 1];
		return territori[indice];
	}

	
	/**
	 * Controlla se due territori sono confinanti, controllando se ci il codice
	 * di t2 e' contenuto nella riga di t1 della matrice di transizione { @link
	 * Costanti.MAPPA}
	 * 
	 * @param t1
	 *            il primo territorio
	 * @param t2
	 *            il secondo territorio
	 * @return true se i territori sono confinanti, false altrimenti
	 */
	public boolean sonoConfinanti(Territorio t1, Territorio t2) throws IllegalArgumentException {
		if (t1.equals(t2)) {
			throw new IllegalArgumentException("I territori devono essere diversi");
		}
		return Utilita.contiene(Costanti.MAPPA[t1.getCodice()], t2.getCodice());
	}

	/**
	 * Date due strade, controlla se sono contigue
	 * 
	 * @param s1
	 *            la prima strada
	 * @param s2
	 *            la seconda strada
	 * @return true se le strade sono contigue, false altrimenti
	 */
	public boolean sonoContigue(Strada s1, Strada s2) {
		if(s1.equals(s2)) {
			return false;
		}
		
		Territorio t1, t2;
		Territorio terrInComune = territorioInComune(s1, s2);
		if (terrInComune != null) {
			t1 = territorioOpposto(terrInComune, s1);
			t2 = territorioOpposto(terrInComune, s2);
			return sonoConfinanti(t1, t2);
		}

		return false;
	}

	/**
	 * Dati un territorio e una strada che lo delimita, restituisce il
	 * territorio opposto a quella strada
	 * 
	 * @param t
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Territorio territorioOpposto(Territorio t, Strada s) throws IllegalArgumentException {
		if (t.equals(s.getTerritorio1())) {
			return s.getTerritorio2();
		}
		if (t.equals(s.getTerritorio2())) {
			return s.getTerritorio1();
		}

		throw new IllegalArgumentException("Il territorio e la strada devono essere vicini");
	}

	/**
	 * Date due strade, restituisce il territorio in comune, null se non ce
	 * l'hanno
	 * 
	 * @param s1
	 *            la prima strada
	 * @param s2
	 *            la seconda strada
	 * @return il territorio in comune, null se non hanno un territorio in
	 *         comune
	 * @throws IllegalArgumentException
	 */
	private Territorio territorioInComune(Strada s1, Strada s2) throws IllegalArgumentException {
		if (s1.equals(s2)) {
			throw new IllegalArgumentException("Le strade devono essere diverse");
		}
		if (s1.getTerritorio1().equals(s2.getTerritorio1()) || s1.getTerritorio1().equals(s2.getTerritorio2())) {
			return s1.getTerritorio1();
		}
		if (s1.getTerritorio2().equals(s2.getTerritorio1()) || s1.getTerritorio2().equals(s2.getTerritorio2())) {
			return s1.getTerritorio2();
		}

		return null;
	}

	/**
	 * Metodo di servizio che crea un array di territori il cui codice è
	 * l'indice dell'array in {@Link Costanti#MAPPA}, il tipo viene letto
	 * in {link @Costanti.TERRITORIO_CODICE}
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
		int codice = 0;
		ArrayList<Strada> temp = new ArrayList<Strada>();
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = i + 1; j < Costanti.NUM_TERRITORI; j++) {
				if (sonoConfinanti(territori[i], territori[j])) {
					temp.add(new Strada(territori[i], territori[j], codice));
					codice++;
				}
			}
		}

		return temp.toArray(new Strada[temp.size()]);
	}

	public static synchronized Mappa getMappa() {
		if (istanza == null) {
			istanza = new Mappa();
		}
		return istanza;
	}

	public Territorio[] getTerritori() {
		return territori;
	}

	public Strada[] getStrade() {
		return strade;
	}

}
