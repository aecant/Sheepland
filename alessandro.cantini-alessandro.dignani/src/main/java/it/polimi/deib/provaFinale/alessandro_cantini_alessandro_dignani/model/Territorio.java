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
		return "Territorio: " + codice + " (" + tipo + ")";
	}

	@Override
	public boolean equals(Object obj) throws RuntimeException{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Territorio))
			return false;
		Territorio other = (Territorio) obj;
		if (!getCodice().equals(other.getCodice()))
			return false;
		if (!getTipo().equals(other.getTipo()))
			throw new RuntimeException("Il codice e' uguale ma il territorio e' diverso");
		return true;
	}

	public TipoTerritorio getTipo() {
		return this.tipo;
	}

	public Integer getCodice() {
		return this.codice;
	}

}
