package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Client;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

public class DatiPartita {
	DatiTerritorio[] territori;
	Pastore[] pastori;
	Integer[] recintiIniziali;
	Integer[] recintiFinali;
	Giocatore giocatoreDiTurno;
	Tessera[] tessere;
	Tessera[] tessereInCima;

	public void aggiornaTerritori() {
		territori = Client.getConnessione().chiediElencoTerritori();
	}

	public void aggiornaPastori() {
		pastori = Client.getConnessione().chiediPastori();
	}

	public void aggiornaRecinti() {
		recintiIniziali = Client.getConnessione().chiediRecintiIniziali();
		recintiFinali = Client.getConnessione().chiediRecintiFinali();
	}
	
	public void setGiocatoreDiTurno(Giocatore giocatoreDiTurno) {
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	public void aggiornaGiocatoreDiTurno() {
		giocatoreDiTurno = Client.getConnessione().chiediGiocatoreDiTurno();
	}

	public void aggiornaTessere() {
		tessere = Client.getConnessione().chiediTessere();
	}

	public void aggiornaTessereInCima() {
		tessereInCima = Client.getConnessione().chiediTessereInCima();
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

	public Giocatore getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	public Tessera[] getTessere() {
		return tessere;
	}

	public Tessera[] getTessereInCima() {
		return tessereInCima;
	}

}
