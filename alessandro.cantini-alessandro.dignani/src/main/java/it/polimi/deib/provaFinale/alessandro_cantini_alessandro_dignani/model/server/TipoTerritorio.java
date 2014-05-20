package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

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
}
