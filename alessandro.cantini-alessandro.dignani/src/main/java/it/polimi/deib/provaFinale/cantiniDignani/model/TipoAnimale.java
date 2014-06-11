package it.polimi.deib.provaFinale.cantiniDignani.model;

public enum TipoAnimale {
	LUPO("il lupo"),
	PECORA_NERA("la pecora nera"),
	PECORA("una pecora"),
	MONTONE("un montone"),
	AGNELLO("un agnello");

	public final String nomeGenerico;

	private TipoAnimale(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
