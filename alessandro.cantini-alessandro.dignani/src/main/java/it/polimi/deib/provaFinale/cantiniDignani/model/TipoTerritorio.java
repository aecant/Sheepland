package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.util.Arrays;

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
     * @return l'array dei tipi delle tessere
     */
    public static TipoTerritorio[] valoriTessere(){
    	return Arrays.copyOfRange(values(), SHEEPSBURG.ordinal() + 1, values().length);
    }
}
