package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

public class TesseraInVendita implements java.io.Serializable {

	private static final long serialVersionUID = 7061099267190131624L;

	private final String giocatore;
	private final TipoTerritorio tipo;
	private final Integer prezzo;

	/**
	 * Crea una tessera in vendita
	 * 
	 * @param giocatore
	 *            il nome del giocatore che mette in vendita la tessera
	 * @param tipo
	 *            il tipo di territorio della tessera
	 * @param prezzo
	 *            il prezzo a cui viene messa in vendita
	 */
	public TesseraInVendita(String giocatore, TipoTerritorio tipo, Integer prezzo) {
		this.giocatore = giocatore;
		this.tipo = tipo;
		this.prezzo = prezzo;
	}

	public String getGiocatore() {
		return giocatore;
	}

	public TipoTerritorio getTipo() {
		return tipo;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

}
