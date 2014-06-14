package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Giocatore implements Serializable {
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
	 * Restituisce una mappa che associa a ogni tipo di tessera il numero di
	 * tessere di quel tipo possedute dal giocatore
	 * 
	 * @return una mappa delle occorrenze delle tessere del giocatore
	 */
	public Map<TipoTerritorio, Integer> numeroTesserePerTipo() {		
		Map<TipoTerritorio, Integer> mappa = new HashMap<TipoTerritorio, Integer>();
		for (TipoTerritorio t : TipoTerritorio.valoriTessere()) {
			mappa.put(t, 0);
		}
		for (Tessera tess : tessere) {
			Utilita.incrementa(mappa, tess.getTipo());
		}

		return mappa;
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

	public List<Tessera> getTessere() {
		return tessere;
	}

	public List<Pastore> getPastori() {
		return pastori;
	}

	public String getNome() {
		return this.nome;
	}

	public Integer getDenaro() {
		return this.denaro;
	}

	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + ", pastori=" + pastori + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Giocatore)) {
			return false;
		}
		Giocatore other = (Giocatore) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
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
