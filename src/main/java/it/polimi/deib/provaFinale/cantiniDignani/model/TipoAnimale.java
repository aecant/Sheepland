package it.polimi.deib.provaFinale.cantiniDignani.model;

public enum TipoAnimale {
	LUPO("il lupo"),
	PECORA_NERA("la pecora nera"),
	PECORA("una pecora"),
	MONTONE("un montone"),
	AGNELLO("un agnello");

	private final String nomeGenerico;

	private TipoAnimale(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}
	
	public String getNomeGenerico() {
		return nomeGenerico;
	}

	@Override
	public String toString() {
		if(this == PECORA_NERA) {
			return "pecora nera";
		}
		return name().toLowerCase();
	}

}
