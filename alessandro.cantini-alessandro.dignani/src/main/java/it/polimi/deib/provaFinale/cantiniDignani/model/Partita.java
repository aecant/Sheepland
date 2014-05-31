package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.util.ArrayList;
import java.util.List;

public class Partita {
	private Mazzo mazzo;
	private Animale lupo;
	private Gregge gregge;
	private InsiemeDiRecinti recinti;
	private ArrayList<Giocatore> giocatori;

	public Partita(List<String> nomiGiocatori) {
		mazzo = new Mazzo();
		lupo = new Animale(Costanti.POS_INIZIALE_LUPO);
		gregge = new Gregge();
		recinti = new InsiemeDiRecinti();
		giocatori = new ArrayList<Giocatore>();
		for (String nome : nomiGiocatori) {
			giocatori.add(new Giocatore(nome));
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
		ArrayList<PedinaSuStrada> pedine = new ArrayList<PedinaSuStrada>();
		pedine.addAll(recinti.getListaRecinti());
		pedine.addAll(getPastori());

		for (PedinaSuStrada p : pedine) {
			if (p.getStrada().equals(s)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Restituisce la lista dei pastori, costruita scorrendo la lista dei
	 * giocatori
	 * 
	 * @return la lista dei pastori
	 */
	public ArrayList<Pastore> getPastori() {
		ArrayList<Pastore> pastori = new ArrayList<Pastore>();
		for (Giocatore g : giocatori) {
			pastori.addAll(g.getPastori());
		}

		return pastori;
	}

	public Gregge getGregge() {
		return gregge;
	}

	/**
	 * Restitusice una copia della lista dei giocatori
	 * 
	 * @return una copia della lista dei giocatori
	 */
	public ArrayList<Giocatore> getGiocatori() {
		return Utilita.copia(giocatori);
	}

	public InsiemeDiRecinti getRecinti() {
		return recinti;
	}

	public Mazzo getMazzo() {
		return mazzo;
	}

	public Animale getLupo() {
		return lupo;
	}

}
