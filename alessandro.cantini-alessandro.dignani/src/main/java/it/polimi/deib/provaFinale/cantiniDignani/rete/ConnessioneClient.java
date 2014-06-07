package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public interface ConnessioneClient {

	public void inizializza();

	public DatiPartita scaricaDatiPartita();

	public void registraGiocatore(String nome);

	public void inviaMossa(Mossa mossaScelta);

	public DatiTerritorio[] chiediElencoTerritori();

	public Giocatore[] chiediGiocatori();

	public Pastore[] chiediPastori();

	public Integer[] chiediRecintiIniziali();

	public Integer[] chiediRecintiFinali();

	public String chiediGiocatoreDiTurno();

	public Tessera[] chiediTessere();

	public Tessera[] chiediTessereInCima();

	
	
}
