package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe;

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
		TipoTerritorio[] valoriTessere = { MONTAGNA, DESERTO, LAGO, BOSCO, PASCOLO, CAMPO };
		return valoriTessere;
	}
	
	@Override
	public String toString() {
		return UtilitaStringhe.inizialeMaiuscola(this.name());
	}

}
