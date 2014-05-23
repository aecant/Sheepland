package model;

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
