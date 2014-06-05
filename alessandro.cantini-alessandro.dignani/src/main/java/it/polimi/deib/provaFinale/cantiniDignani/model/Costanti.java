package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Costanti {
	private Costanti() {
	}
	
	public static final int ETA_MAX_AGNELLO = 2;
	
	public static final int NUM_MAX_GIOCATORI = 4;

	public static final int MAX_VALORE_TESSERA = 4;
	public static final int NUM_TESSERE_PER_TIPO = MAX_VALORE_TESSERA + 1;

	public static final int DENARO_INIZIALE = 20;
	public static final int DENARO_INIZIALE_DUE_GIOCATORI = 30;

	public static final int NUM_RECINTI_INIZIALI = 20;
	public static final int NUM_RECINTI_FINALI = 12;
	
	public static final int NUM_PASTORI_DUE_GIOCATORI = 2;
	
	/**
	 * matrice di transizione fra un territorio e un altro confinante
	 * l'indice delle righe corrisponde al codice del territorio
	 * l'indice delle colonne rappresenta il valore del dado
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
	 * array che fornisce la corrispondenza tra il codice di un territorio e il suo tipo,
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
	
	
	public static final int NUM_TERRITORI = MAPPA.length;

	public static final Territorio POS_INIZIALE_LUPO = Mappa.getMappa().getTerritori()[0];
	public static final Territorio POS_INIZIALE_PECORA_NERA = Mappa.getMappa().getTerritori()[0];
	
	public static final long SECONDI_TIMER_PARTITA = 30;
	
}
