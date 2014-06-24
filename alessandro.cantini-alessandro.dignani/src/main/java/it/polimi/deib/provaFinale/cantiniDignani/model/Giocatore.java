package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Il giocatore che gioca a una partita di Sheepland
 */
public class Giocatore implements Serializable {
	private static final long serialVersionUID = -8973926581885818571L;

	private final String nome;
	private Integer denaro;
	private final List<Pastore> pastori;
	private final List<Tessera> tessere;

	/**
	 * Crea un giocatore
	 * 
	 * @param nome
	 *            il nome del giocatore
	 */
	public Giocatore(String nome) {
		if (nome == null) {
			throw new IllegalArgumentException("Il nome del giocatore non puo' essere null");
		}
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
	public void sottraiDenaro(int quantita) {
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
	 * Toglie al giocatore una tessera di un certo tipo
	 * 
	 * @param tipo
	 *            il tipo territorio della tessera
	 * @return la tessera tolta al giocatore
	 */
	public Tessera rimuoviTessera(TipoTerritorio tipo) {
		Iterator<Tessera> iter = tessere.iterator();
		while (iter.hasNext()) {
			Tessera tess = iter.next();
			if (tess.getTipo() == tipo) {
				iter.remove();
				return tess;
			}
		}
		throw new IllegalArgumentException(tipo + " non presente");
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
		if (pastore == null) {
			throw new IllegalArgumentException("pastore non puo' essere null");
		}
		pastori.add(pastore);
	}

	/**
	 * Restituisce le tessere
	 * @return le tessere
	 */
	public List<Tessera> getTessere() {
		return tessere;
	}
	
	/**
	 * Restituisce i pastori di un giocatore
	 * @return i pastori
	 */
	public List<Pastore> getPastori() {
		return pastori;
	}

	/**
	 * Restituisce il nome del giocatore
	 * @return il nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il denaro del giocatore
	 * @return il denaro
	 */
	public Integer getDenaro() {
		return this.denaro;
	}
	
	/**
	 * Restituisce una stringa che rappresenta il giocatore
	 */
	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + ", pastori=" + pastori + "]";
	}

	/**
	 * Restituisce l'hash code di un giocatore
	 */
	@Override
	public int hashCode() {
		return Utilita.hashCodeNome(nome);
	}

	/**
	 * Compara il giocatore con un altro oggetto, usando il nome
	 */
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
