package it.polimi.deib.provaFinale.cantiniDignani.model;

public class CostantiModel {
	
	/**
	 * L'eta' massima dell'agnello prima di diventare adulto
	 */
	public static final int ETA_MAX_AGNELLO = 2;

	/**
	 * Il numero minimo di giocatori per effettuare una partita
	 */
	public static final int NUM_MIN_GIOCATORI = 2;

	/**
	 * Il numero massimo di giocatori che possono partecipare a una partita
	 */
	public static final int NUM_MAX_GIOCATORI = 4;

	/**
	 * Il valore massimo delle tessere
	 */
	public static final int MAX_VALORE_TESSERA = 4;

	/**
	 * Il numero di tessere per ogni tipo di territorio
	 */
	public static final int NUM_TESSERE_PER_TIPO = MAX_VALORE_TESSERA + 1;

	/**
	 * Il denaro iniziale di ciascun giocatore in una partita con piu' di un
	 * giocatore
	 */
	public static final int DENARO_INIZIALE = 20;

	/**
	 * Il denaro iniziale di ciascun giocatore in una partita con due giocatori
	 */
	public static final int DENARO_INIZIALE_DUE_GIOCATORI = 30;

	/**
	 * Il numero delle mosse a disposizione di un giocatore in ogni turno
	 */
	public static final int NUM_MOSSE_PER_TURNO = 3;

	/**
	 * Il numero di recinti iniziali
	 */
	public static final int NUM_RECINTI_INIZIALI = 20;

	/**
	 * Il numero di recinti finali
	 */
	public static final int NUM_RECINTI_FINALI = NUM_MOSSE_PER_TURNO * NUM_MAX_GIOCATORI;

	/**
	 * Il numero di pastori per due giocatori
	 */
	public static final int NUM_PASTORI_DUE_GIOCATORI = 2;

	/**
	 * La matrice di transizione fra un territorio e un altro confinante
	 * L'indice delle righe corrisponde al codice del territorio
	 * L'indice delle colonne rappresenta il valore del dado
	 */
	public static final Integer[][] MAPPA = {
		{1,4,7,10,13,16},
		{0,18,2,4,16,17},
		{18,3,1,2,4,2},
		{5,2,4,3,3,3},
		{7,0,3,1,2,5},
		{3,7,5,5,6,4},
		{8,6,6,7,5,6},
		{4,5,0,6,10,8},
		{6,10,8,8,9,7},
		{10,11,9,9,8,9},
		{9,8,11,0,7,13},
		{12,9,10,13,11,11},
		{11,13,12,14,12,12},
		{16,12,14,11,0,10},
		{15,16,13,12,14,14},
		{14,17,16,15,15,15},
		{13,14,15,17,1,0},
		{17,15,18,16,17,1},
		{2,1,17,18,18,18}
		};
	
	/**
	 * Array che fornisce la corrispondenza tra il codice di un territorio e il suo tipo,
	 * l'indice dell'array corrisponde al codice del territorio
	 * dipende dall'implementazione di Mappa
	 */
	public static final TipoTerritorio[] TERRITORIO_CODICE = {
		TipoTerritorio.SHEEPSBURG,
		TipoTerritorio.MONTAGNA,
		TipoTerritorio.MONTAGNA,
		TipoTerritorio.MONTAGNA,
		TipoTerritorio.DESERTO,
		TipoTerritorio.DESERTO,
		TipoTerritorio.DESERTO,
		TipoTerritorio.LAGO,
		TipoTerritorio.LAGO,
		TipoTerritorio.LAGO,
		TipoTerritorio.BOSCO,
		TipoTerritorio.BOSCO,
		TipoTerritorio.BOSCO,
		TipoTerritorio.PASCOLO,
		TipoTerritorio.PASCOLO,
		TipoTerritorio.PASCOLO,
		TipoTerritorio.CAMPO,
		TipoTerritorio.CAMPO,
		TipoTerritorio.CAMPO,
	};

	/**
	 * Il numero di territori nella mappa
	 */
	public static final int NUM_TERRITORI = MAPPA.length;

	/**
	 * Il numero di strade nella mappa
	 */
	public static final int NUM_STRADE = 42;

	/**
	 * La posizione iniziale del lupo
	 */
	public static final Territorio POS_INIZIALE_LUPO = Mappa.getMappa().getTerritori()[0];

	/**
	 * La posizione iniziale della pecora nera
	 */
	public static final Territorio POS_INIZIALE_PECORA_NERA = Mappa.getMappa().getTerritori()[0];

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private CostantiModel() {
	}
	
}
