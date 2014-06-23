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

	public void setGiocatori(Giocatore[] giocatori) {
		if (giocatori == null) {
			throw new IllegalArgumentException("i giocatori non possono essere null");
		}
		this.giocatori = giocatori.clone();
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
		if (territori == null) {
			throw new IllegalArgumentException("i territori non possono essere null");
		}
		this.territori = territori.clone();
	}

	public Integer[] getRecinti() {
		return recinti;
	}

	public void setRecinti(Integer[] recinti) {
		if (territori == null) {
			throw new IllegalArgumentException("i recinti non possono essere null");
		}
		this.recinti = recinti.clone();
	}

	public String getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	public void setGiocatoreDiTurno(String giocatoreDiTurno) {
		if (giocatoreDiTurno == null) {
			throw new IllegalArgumentException("il giocatore di turno non puo' essere null");
		}
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

	public void setTessereInCima(Tessera[] tessereInCima) {
		if (tessereInCima == null) {
			throw new IllegalArgumentException("le tessere in cima non possono essere null");
		}
		this.tessereInCima = tessereInCima.clone();
	}

}
