package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Giocatore implements Serializable{
	private static final long serialVersionUID = -8973926581885818571L;
	
	private String nome;
	private Integer denaro;
	private List<Pastore> pastori;
	private List<Tessera> tessere;

	public Giocatore(String nome) {
		this.nome = nome;
		this.denaro = 0;

		pastori = new ArrayList<Pastore>();
		tessere = new ArrayList<Tessera>();
	}

	/**
	 * Aggiunge denaro al giocatore
	 * 
	 * @param quantita
	 *            la quantita' di denaro da aggiungere
	 */
	public void aggiungiDenaro(int quantita) {
		denaro += quantita;
	}

	/**
	 * Sottrae denaro al giocatore
	 * 
	 * @param quantita
	 *            la quantita' di denaro da sottrarre
	 * @throws DenaroInsufficienteException
	 *             se il pastore non dispone di abbastanza denaro
	 */
	public void sottraiDenaro(int quantita) throws DenaroInsufficienteException {
		if (quantita > denaro) {
			throw new DenaroInsufficienteException();
		}
		denaro -= quantita;
	}

	/**
	 * Aggiunge una tessera al giocatore
	 * 
	 * @param tessera
	 *            la tessera da aggiungere
	 */
	public void aggiungiTessera(Tessera tessera) {
		tessere.add(tessera);
	}

	/**
	 * Aggiunge un pastore
	 * 
	 * @param pastore
	 *            il pastore da aggiungere
	 */
	public void aggiungiPastore(Pastore pastore) {
		pastori.add(pastore);
	}

	/**
	 * Restituisce una copia delle tessere
	 * 
	 * @return una copia dell'ArrayList delle tessere possedute dal giocatore
	 */
	public List<Tessera> getTessere() {
		return Utilita.copia(tessere);
	}

	/**
	 * Restituisce una copia dei pastori
	 * 
	 * @return una copia dell'ArrayList delle tessere possedute dal giocatore
	 */
	public List<Pastore> getPastori() {
		return Utilita.copia(pastori);
	}

	public String getNome() {
		return this.nome;
	}

	public Integer getDenaro() {
		return this.denaro;
	}

	/**
	 * Eccezione che segnala che il denaro del pastore e' insufficiente per
	 * effettuare un'operazione che richiede un costo
	 */
	public class DenaroInsufficienteException extends IllegalArgumentException {

		private static final long serialVersionUID = -270481645032368246L;

		public DenaroInsufficienteException() {
			super();
		}

		public DenaroInsufficienteException(String s) {
			super(s);
		}
	}

}
