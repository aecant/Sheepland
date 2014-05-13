package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;
import java.util.List;

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
	}

	public void sottraiDenaro() {
	}

	public void mettiInVendita(Tessera tessera) {
	}

	public void compra(Tessera tessera) {
	}

	public void prendiTessera(TipoTerritorio tipo) {
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
}
