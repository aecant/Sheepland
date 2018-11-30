package it.polimi.deib.provaFinale.cantiniDignani.model;

public enum TipoTerritorio {
	SHEEPSBURG,
	MONTAGNA,
	DESERTO,
	LAGO,
	BOSCO,
	PASCOLO,
	CAMPO;

	/**
	 * Restituisce l'array di tutti i tipi territori a parte Sheepsburg
	 * 
	 * @return l'array dei tipi delle tessere
	 */
	public static TipoTerritorio[] valoriTessere() {
		return new TipoTerritorio[] { MONTAGNA, DESERTO, LAGO, BOSCO, PASCOLO, CAMPO };
	}

	@Override
	public String toString() {
		return name().toLowerCase().substring(0, 1).toUpperCase() + name().toLowerCase().substring(1);
	}

}
