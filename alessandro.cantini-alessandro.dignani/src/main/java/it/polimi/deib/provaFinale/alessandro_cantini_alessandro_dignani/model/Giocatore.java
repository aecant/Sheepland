package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Giocatore {
	private String nome;
	private int denaro;
	private Pastore pastore;
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

	public Pastore getPastore() {
		return pastore;
	}

	public void setPastore(Pastore pastore) {
		this.pastore = pastore;
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
