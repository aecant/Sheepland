package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public interface ConnessioneClient {

	public void inizializza();

	public void registraGiocatore(String nome);

	public void inviaMossa(Mossa mossaScelta);

	public DatiPartita scaricaDatiPartita();

	public DatiTerritorio[] chiediElencoTerritori();

	public Giocatore[] chiediGiocatori();

	public Integer[] chiediRecintiIniziali();

	public Integer[] chiediRecintiFinali();

	public String chiediGiocatoreDiTurno();

	public Tessera[] chiediTessereInCima();

}
