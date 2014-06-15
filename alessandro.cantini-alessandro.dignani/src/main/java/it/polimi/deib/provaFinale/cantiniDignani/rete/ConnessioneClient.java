package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public interface ConnessioneClient {

	void inizializza();

	void registraGiocatore(String nome);

	void inviaMossa(int mossaScelta);

	DatiPartita scaricaDatiPartita();

	DatiTerritorio[] chiediDatiTerritori();

	Giocatore[] chiediGiocatori();

	Integer[] chiediRecintiIniziali();

	Integer[] chiediRecintiFinali();

	String chiediGiocatoreDiTurno();

	Tessera[] chiediTessereInCima();

}
