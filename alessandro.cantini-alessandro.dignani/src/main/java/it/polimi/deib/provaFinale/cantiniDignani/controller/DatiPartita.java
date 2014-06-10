package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.util.ArrayList;
import java.util.Collection;

public class DatiPartita {
	private DatiTerritorio[] territori;
	private Pastore[] pastori;
	private Integer[] recintiIniziali;
	private Integer[] recintiFinali;
	private String giocatoreDiTurno;
	private Tessera[] tessereInCima;
	private Giocatore[] giocatori;

	public DatiPartita(Partita partita) {
		territori = Estrattore.datiTerritori(partita);
		pastori = Estrattore.pastori(partita);
		recintiIniziali = Estrattore.recintiIniziali(partita);
		recintiFinali = Estrattore.recintiFinali(partita);
		giocatoreDiTurno = partita.getGiocatoreDiTurno().getNome();
		tessereInCima = Estrattore.tessereInCima(partita);
		giocatori = Estrattore.giocatori(partita);
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	public Giocatore getGiocatore(String nome) {
		for(Giocatore g : giocatori) {
			if(g.getNome().equals(nome)) {
				return g;
			}
		}
		throw new IllegalArgumentException("Il giocatore non e' presente");
	}
	
	public Collection<String> getNomiGiocatori() {
		Collection<String> nomi = new ArrayList<String>();
		for (Giocatore g : giocatori) {
			nomi.add(g.getNome());
		}
		return nomi;
	}

	public void aggiornaGiocatori() {
		giocatori = ClientMain.getConnessione().chiediGiocatori();
	}

	public void aggiornaTerritori() {
		territori = ClientMain.getConnessione().chiediElencoTerritori();
	}

	public void aggiornaPastori() {
		pastori = ClientMain.getConnessione().chiediPastori();
	}

	public void aggiornaRecinti() {
		recintiIniziali = ClientMain.getConnessione().chiediRecintiIniziali();
		recintiFinali = ClientMain.getConnessione().chiediRecintiFinali();
	}

	public void setGiocatoreDiTurno(String giocatore) {
		this.giocatoreDiTurno = giocatore;
	}

	public void aggiornaGiocatoreDiTurno() {
		giocatoreDiTurno = ClientMain.getConnessione().chiediGiocatoreDiTurno();
	}

	public void aggiornaTessereInCima() {
		tessereInCima = ClientMain.getConnessione().chiediTessereInCima();
	}

	public DatiTerritorio[] getTerritori() {
		return territori;
	}

	public Pastore[] getPastori() {
		return pastori;
	}

	public Integer[] getRecintiIniziali() {
		return recintiIniziali;
	}

	public Integer[] getRecintiFinali() {
		return recintiFinali;
	}

	public String getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

}
