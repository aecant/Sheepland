package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	public Giocatore getGiocatore(String nome) {
		for (Giocatore g : giocatori) {
			if (g.getNome().equals(nome)) {
				return g;
			}
		}
		throw new IllegalArgumentException("Il giocatore " + nome + " non e' presente");
	}

	public Collection<String> getNomiGiocatori() {
		Collection<String> nomi = new ArrayList<String>();
		for (Giocatore g : giocatori) {
			nomi.add(g.getNome());
		}
		return nomi;
	}

	public DatiTerritorio[] getTerritori() {
		return territori;
	}

	public void setTerritori(DatiTerritorio[] territori) {
		this.territori = territori;
	}

	public Integer[] getRecinti() {
		return recinti;
	}

	public void setRecinti(Integer[] recinti) {
		this.recinti = recinti;
	}

	public String getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	public void setGiocatoreDiTurno(String giocatoreDiTurno) {
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

	public void setTessereInCima(Tessera[] tessereInCima) {
		this.tessereInCima = tessereInCima;
	}

	public void setGiocatori(Giocatore[] giocatori) {
		this.giocatori = giocatori;
	}

}
