package rete;

import model.Giocatore;
import model.Pastore;
import model.Tessera;
import controller.ClientMain;

public class DatiPartita {
	DatiTerritorio[] territori;
	Pastore[] pastori;
	Integer[] recintiIniziali;
	Integer[] recintiFinali;
	String giocatoreDiTurno;
	Tessera[] tessere;
	Tessera[] tessereInCima;
	Giocatore[] giocatori;
	
	public Giocatore[] getGiocatori() {
		return giocatori;
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

	public void aggiornaTessere() {
		tessere = ClientMain.getConnessione().chiediTessere();
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

	public Tessera[] getTessere() {
		return tessere;
	}

	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

}
