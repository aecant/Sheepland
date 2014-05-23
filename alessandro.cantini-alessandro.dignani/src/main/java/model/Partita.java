package model;

import java.util.ArrayList;

import controller.Utilita;

public class Partita {
	private Mazzo mazzo;
	private Animale lupo;
	private Gregge gregge;
	private InsiemeDiRecinti recinti;
	private ArrayList<Giocatore> giocatori;

	public Partita() {
		mazzo = new Mazzo();
		lupo = new Animale(Costanti.POS_INIZIALE_LUPO);
		gregge = new Gregge();
		recinti = new InsiemeDiRecinti();
		giocatori = new ArrayList<Giocatore>();
	}

	public boolean stradaLibera(Strada s) {
		ArrayList<PedinaSuStrada> pedine = new ArrayList<PedinaSuStrada>();
		pedine.addAll(recinti.getListaRecinti());
		pedine.addAll(getPastori());

		for (PedinaSuStrada p : pedine)
			if (p.getStrada().equals(s))
				return false;

		return true;
	}
	
	public void aggiungiGiocatore(String nome) {
		giocatori.add(new Giocatore(nome));
	}

	/**
	 * Restituisce la lista dei pastori, costruita scorrendo la lista dei
	 * giocatori
	 * 
	 * @return
	 */
	public ArrayList<Pastore> getPastori() {
		ArrayList<Pastore> pastori = new ArrayList<Pastore>();
		for (Giocatore g : giocatori)
			pastori.addAll(g.getPastori());

		return pastori;
	}

	public Gregge getGregge() {
		return gregge;
	}

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
