package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Giocatore {
	private String nome;
	private int denaro;
	private ArrayList<Pastore> pastori;
	private ArrayList<Tessera> tessere = new ArrayList<Tessera>();

	public Giocatore(String nome) {
		this.nome = nome;
		this.denaro = 0;
	}

	public void aggiungiDenaro(int quantita) {
		denaro += quantita;
	}

	public void aggiungiTessera(Tessera tessera) {
		tessere.add(tessera);
	}

	public String getNome() {
		return this.nome;
	}

	public int getDenaro() {
		return this.denaro;
	}

	
	public ArrayList<Pastore> getPastori() {
		return pastori;
	}

	public void aggiungiPastore(Pastore p) throws RuntimeException{
		if(pastori.size() >= Costanti.NUM_PASTORI_DUE_GIOCATORI)
			throw new RuntimeException("Il numero di pastori non puo' essere maggiore di "+Costanti.NUM_PASTORI_DUE_GIOCATORI);
		pastori.add(p);
	}
	

	public class DenaroInsufficienteException extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		public DenaroInsufficienteException() {
			super();
		}

		public DenaroInsufficienteException(String s) {
			super(s);
		}
	}

}
