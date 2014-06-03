package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Territorio {

	private int codice;
	private TipoTerritorio tipo;

	public Territorio(int codice, TipoTerritorio tipo) throws IllegalArgumentException {
		if (codice < 0 && codice > Costanti.NUM_TERRITORI - 1)
			throw new IllegalArgumentException("Il tipo dev'essere compreso fra 0 e " + (Costanti.NUM_TERRITORI - 1));
		this.tipo = tipo;
		this.codice = codice;
	}

	public TipoTerritorio getTipo() {
		return this.tipo;
	}

	public Integer getCodice() {
		return this.codice;
	}

	@Override
	public String toString() {
		return "Territorio: " + codice + " (" + tipo + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
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
		if (tipo != other.tipo)
			throw new RuntimeException("Due territori non possono avere codice uguale e tipo diverso!");

		return true;
	}


}
