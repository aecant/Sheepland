package it.polimi.deib.provaFinale.cantiniDignani.model;

public enum TipoTerritorio {
	SHEEPSBURG,
	MONTAGNA,
	DESERTO,
	LAGO,
	BOSCO,
	PASCOLO,
	CAMPO;

	public String toString() {
		return this.name().toLowerCase();
	}

	/**
	 * Restituisce l'array di tutti i tipi territori a parte Sheepsburg
	 * 
	 * @return l'array dei tipi delle tessere
	 */
	public static TipoTerritorio[] valoriTessere() {
		TipoTerritorio[] valoriTessere = { MONTAGNA, DESERTO, LAGO, BOSCO, PASCOLO, CAMPO };
		return valoriTessere;
	}
}
