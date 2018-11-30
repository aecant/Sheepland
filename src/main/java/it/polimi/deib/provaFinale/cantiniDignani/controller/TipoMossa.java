package it.polimi.deib.provaFinale.cantiniDignani.controller;

public enum TipoMossa {
	MUOVI_PASTORE("Muovi pastore"),
	MUOVI_PECORA("Muovi pecora"),
	ACQUISTA_TESSERA("Acquista tessera"),
	ACCOPPIA("Accoppia"),
	ABBATTI("Abbatti");

	private final String nome;

	private TipoMossa(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce il nome della mossa
	 */
	@Override
	public String toString() {
		return getNome();
	}

	/**
	 * Restituisce il nome della mossa
	 */
	public String getNome() {
		return nome;
	}
}
