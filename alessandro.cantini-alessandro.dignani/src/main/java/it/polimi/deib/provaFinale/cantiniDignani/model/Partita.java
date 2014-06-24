package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Contiene i dati e i metodi per svolgere una partita a Sheepland
 */
public class Partita {
	private Mazzo mazzo;
	private Animale lupo;
	private Gregge gregge;
	private InsiemeDiRecinti recinti;
	private List<Giocatore> giocatori;
	private Giocatore giocatoreDiTurno;

	/**
	 * Crea una partita
	 * 
	 * @param nomiGiocatori
	 *            i nomi dei giocatori.
	 */
	public Partita(Collection<String> nomiGiocatori) {
		mazzo = new Mazzo();
		lupo = new Animale(CostantiModel.POS_INIZIALE_LUPO);
		gregge = new Gregge();
		recinti = new InsiemeDiRecinti();
		giocatori = new ArrayList<Giocatore>();
		for (String nome : nomiGiocatori) {
			giocatori.add(new Giocatore(nome));
		}

		Collections.shuffle(giocatori);

		giocatoreDiTurno = giocatori.get(0);

		aggiungiPastoreAiGiocatori();
		if (giocatori.size() == 2) {
			aggiungiPastoreAiGiocatori();
		}
	}

	/**
	 * Verifica se una strada e' libera
	 * 
	 * @param s
	 *            la strada da analizzare
	 * @return true se la strada e' libera, false se e' occupata
	 */
	public boolean stradaLibera(Strada s) {
		List<PedinaSuStrada> pedine = getTutteLePedineSuStrada();

		for (PedinaSuStrada p : pedine) {
			if (s.equals(p.getStrada())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Verifica se una strada e' occupata da una pecora o dalla pecora nera. Il
	 * lupo non e' considerato nel controllo.
	 * 
	 * @return true se il territorio e' libero, false se e' occupato
	 */
	public boolean territorioLibero(Territorio t) {
		for (Pecora p : gregge.getPecore()) {
			if (p.getPosizione().equals(t)) {
				return false;
			}
		}
		if (gregge.getPecoraNera().equals(t)) {
			return false;
		}
		return true;
	}

	/**
	 * Restituisce una lista con tutte le pedine su strada
	 * 
	 * @return la lista di tutte le pedine su strada della partita
	 */
	public List<PedinaSuStrada> getTutteLePedineSuStrada() {
		List<PedinaSuStrada> pedine = new ArrayList<PedinaSuStrada>();
		pedine.addAll(getPastori());
		pedine.addAll(recinti.getListaRecinti());
		return pedine;
	}

	/**
	 * Restituisce la lista dei pastori, costruita scorrendo la lista dei
	 * giocatori
	 * 
	 * @return la lista dei pastori
	 */
	public List<Pastore> getPastori() {
		List<Pastore> pastori = new ArrayList<Pastore>();
		for (Giocatore g : giocatori) {
			for (Pastore p : g.getPastori()) {
				if (p.getStrada() != Mappa.STRADA_INESISTENTE) {
					pastori.add(p);
				}
			}
		}

		return pastori;
	}

	/**
	 * Restituisce il giocatore che possiede un certo pastore
	 * 
	 * @param pastore
	 *            il pastore del giocatore
	 * @return il giocatore che possiede il pastore
	 */
	public Giocatore getGiocatore(Pastore pastore) {
		for (Giocatore g : giocatori) {
			for (Pastore p : g.getPastori()) {
				if (p.equals(pastore)) {
					return g;
				}
			}
		}

		throw new IllegalArgumentException("Problema: il " + pastore + " non e' presente nella " + this);
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
		throw new IllegalArgumentException("Non c'e' un giocatore con il nome " + nome);
	}

	/**
	 * Restituisce il gregge
	 * 
	 * @return il gregge
	 */
	public Gregge getGregge() {
		return gregge;
	}

	/**
	 * Restituisce i giocatori
	 * 
	 * @return i giocatori
	 */
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	/**
	 * Restituisce i recinti
	 * 
	 * @return i recitni
	 */
	public InsiemeDiRecinti getRecinti() {
		return recinti;
	}

	/**
	 * Restituisce il mazzo
	 * 
	 * @return il mazzo
	 */
	public Mazzo getMazzo() {
		return mazzo;
	}

	/**
	 * Restituisce il lupo
	 * 
	 * @return il lupo
	 */
	public Animale getLupo() {
		return lupo;
	}

	/**
	 * Restituisce il giocatore di turno
	 * 
	 * @return il giocatore di turno
	 */
	public Giocatore getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	/**
	 * Cambia il giocatore di turno
	 * 
	 * @param giocatoreDiTurno
	 *            il giocatore da sostituire
	 */
	public void setGiocatoreDiTurno(Giocatore giocatoreDiTurno) {
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	private void aggiungiPastoreAiGiocatori() {
		for (int i = 0; i < giocatori.size(); i++) {
			giocatori.get(i).aggiungiPastore(new Pastore(Mappa.STRADA_INESISTENTE, ColorePastore.values()[i]));
		}
	}

	/**
	 * Restituisce una stringa con i giocatori della partita
	 */
	@Override
	public String toString() {
		return "partita giocata da " + giocatori;
	}

}
