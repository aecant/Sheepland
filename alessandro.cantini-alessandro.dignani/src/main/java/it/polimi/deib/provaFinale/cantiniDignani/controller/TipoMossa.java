package it.polimi.deib.provaFinale.cantiniDignani.controller;

public enum TipoMossa {
	MUOVI_PASTORE("Muovi pastore"),
	MUOVI_PECORA("Muovi pecora"),
	ACQUISTA_TESSERA("Acquista tessera"),
	ACCOPPIA("Accoppia"),
	ABBATTI("Abbatti");
	
	public final String nome;
	
	private TipoMossa(String nome) {
		this.nome = nome;
	}
}
