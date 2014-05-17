package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Partita {
	private Mazzo mazzo;
	private Animale lupo;
	private Gregge gregge;
	private Recinti recinti;
	private ArrayList<Giocatore> giocatori;
	 
	
	public Partita() {
		mazzo = new Mazzo();
		lupo = new Animale(Costanti.POS_INIZIALE_LUPO);
		gregge = new Gregge();
		recinti = new Recinti();
		giocatori = new ArrayList<Giocatore>();
	}


	/**
	 * Restituisce la lista delle pecore che stanno su un determinato territorio
	 * @param t
	 * @return
	 */
	public ArrayList<Pecora> pecoreSuTerritorio(Territorio t) {
		ArrayList<Pecora> pecore = new ArrayList<Pecora>();
		
		for(Pecora p : gregge.getPecore()) {
			if(p.getPosizione().equals(t))
				pecore.add(p);
		}		
		return pecore;
	}
	
	
	public boolean stradaLibera(Strada s) {
		ArrayList<PedinaSuStrada> pedine = new ArrayList<PedinaSuStrada>();
		pedine.addAll(recinti.getRecinti());
		pedine.addAll(getPastori());
		
		for(PedinaSuStrada p : pedine)
			if(p.getStrada().equals(s))
				return false;
		
		return true;
	}
	
	/**
	 * Restituisce la lista dei pastori, costruita scorrendo la lista dei giocatori
	 * @return
	 */
	public ArrayList<Pastore> getPastori(){
		ArrayList<Pastore> pastori = new ArrayList<Pastore>();
		for(Giocatore g : giocatori)
			pastori.addAll(g.getPastori());
		
		return pastori;
	}
	
	public Mazzo getMazzo() {
		return mazzo;
	}

	public Animale getLupo() {
		return lupo;
	}

}
