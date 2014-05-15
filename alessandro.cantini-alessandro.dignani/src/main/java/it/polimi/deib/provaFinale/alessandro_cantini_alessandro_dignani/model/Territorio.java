package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Territorio {
	private int codice;
	private TipoTerritorio tipo;

	public Territorio(int codice, TipoTerritorio tipo) throws IllegalArgumentException{
		if(codice < 0 && codice > Costanti.NUM_TERRITORI - 1)
			throw new IllegalArgumentException("Il tipo dev'essere compreso fra 0 e "+ (Costanti.NUM_TERRITORI-1));
		this.tipo = tipo;
		this.codice = codice;
	}

	@Override
	public String toString() {
		return "Codice: " + codice + " (" + tipo + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Territorio))
			return false;
		Territorio other = (Territorio) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	public TipoTerritorio getTipo() {
		return this.tipo;
	}

	public int getCodice() {
		return this.codice;
	}

}
