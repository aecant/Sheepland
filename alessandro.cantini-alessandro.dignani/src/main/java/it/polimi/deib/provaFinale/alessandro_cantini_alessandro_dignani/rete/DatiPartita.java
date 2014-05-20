package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

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
	
	
	public DatiTerritorio[] getTerritori() {
		return territori;
	}
	public void setTerritori(DatiTerritorio[] territori) {
		this.territori = territori;
	}
	public Pastore[] getPastori() {
		return pastori;
	}
	public void setPastori(Pastore[] pastori) {
		this.pastori = pastori;
	}
	public Integer[] getRecintiIniziali() {
		return recintiIniziali;
	}
	public void setRecintiIniziali(Integer[] recintiIniziali) {
		this.recintiIniziali = recintiIniziali;
	}
	public Integer[] getRecintiFinali() {
		return recintiFinali;
	}
	public void setRecintiFinali(Integer[] recintiFinali) {
		this.recintiFinali = recintiFinali;
	}
	public Giocatore getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}
	public void setGiocatoreDiTurno(Giocatore giocatoreDiTurno) {
		this.giocatoreDiTurno = giocatoreDiTurno;
	}
	public Tessera[] getTessere() {
		return tessere;
	}
	public void setTessere(Tessera[] tessere) {
		this.tessere = tessere;
	}
	
}
