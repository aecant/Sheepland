package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Contiene i dati di una partita
 */
public class DatiPartita implements Serializable {
	private static final long serialVersionUID = -4880327134106355480L;

	private DatiTerritorio[] territori;
	private Integer[] recinti;
	private String giocatoreDiTurno;
	private Tessera[] tessereInCima;
	private Giocatore[] giocatori;

	protected DatiPartita(Partita partita) {
		territori = Estrattore.datiTerritori(partita);
		recinti = Estrattore.recinti(partita);
		giocatoreDiTurno = partita.getGiocatoreDiTurno().getNome();
		tessereInCima = Estrattore.tessereInCima(partita);
		giocatori = Estrattore.giocatori(partita);
	}

	/**
	 * Restituisce i giocatori
	 * 
	 * @return l'array dei giocatori
	 */
	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	/**
	 * Cambia il valore dei giocatori
	 * 
	 * @param giocatori
	 *            i giocatori da sostituire
	 */
	public void setGiocatori(Giocatore[] giocatori) {
		if (giocatori == null) {
			throw new IllegalArgumentException("i giocatori non possono essere null");
		}
		this.giocatori = giocatori.clone();
	}

	/**
	 * Restituisce il giocatore con un certo nome
	 * 
	 * @param nome
	 *            il nome del giocatore
	 * @return il giocatore con un certo nome
	 */
	public Giocatore getGiocatore(String nome) {
		for (Giocatore g : giocatori) {
			if (g.getNome().equals(nome)) {
				return g;
			}
		}
		throw new IllegalArgumentException("Il giocatore " + nome + " non e' presente");
	}

	/**
	 * Restituisce la collezione che contiene tutti i nomi dei giocatori
	 * 
	 * @return i nomi dei giocatori
	 */
	public Collection<String> getNomiGiocatori() {
		Collection<String> nomi = new ArrayList<String>();
		for (Giocatore g : giocatori) {
			nomi.add(g.getNome());
		}
		return nomi;
	}

	/**
	 * Restituisce i dati dei territori
	 * 
	 * @return i dati dei territori
	 */
	public DatiTerritorio[] getTerritori() {
		return territori;
	}

	/**
	 * Cambia i dati dei territori
	 * 
	 * @param territori
	 *            i territori da sostituire
	 */
	public void setTerritori(DatiTerritorio[] territori) {
		if (territori == null) {
			throw new IllegalArgumentException("i territori non possono essere null");
		}
		this.territori = territori.clone();
	}

	/**
	 * Restituisce i codici delle strade su cui sono i recinti
	 * 
	 * @return i recinti
	 */
	public Integer[] getRecinti() {
		return recinti;
	}

	/**
	 * Cambia i recinti
	 * 
	 * @param recinti
	 *            i recinti da sostituire
	 */
	public void setRecinti(Integer[] recinti) {
		if (territori == null) {
			throw new IllegalArgumentException("i recinti non possono essere null");
		}
		this.recinti = recinti.clone();
	}

	/**
	 * Restituisce il giocatore che sta giocando
	 * 
	 * @return il giocatore di turno
	 */
	public String getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	/**
	 * Cambia il giocatore di turno
	 * 
	 * @param giocatoreDiTurno
	 *            il giocatore di turno da sostituire
	 */
	public void setGiocatoreDiTurno(String giocatoreDiTurno) {
		if (giocatoreDiTurno == null) {
			throw new IllegalArgumentException("il giocatore di turno non puo' essere null");
		}
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	/**
	 * Restituisce le tessere in cima al mazzo
	 * 
	 * @return le tessere in cima al mazzo
	 */
	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

	/**
	 * Cambia le tessera in cima al mazzo
	 * 
	 * @param tessereInCima
	 */
	public void setTessereInCima(Tessera[] tessereInCima) {
		if (tessereInCima == null) {
			throw new IllegalArgumentException("le tessere in cima non possono essere null");
		}
		this.tessereInCima = tessereInCima.clone();
	}

}
