package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.RemoteException;

public interface ConnessioneClient {

	public void inizializza();

	public DatiPartita scaricaDatiPartita();

	public void registraGiocatore(String nome) throws RemoteException;

	public Evento chiediEvento();

	public Mossa[] chiediMosseDisponibili();

	public void inviaMossa(Mossa mossaScelta);

	public DatiTerritorio[] chiediElencoTerritori();

	public Integer[] chiediStradeConfinanti(Integer strada);

	public Integer[] chiediStradeLibere();

	public DatiTerritorio[] chediPecoreTerritoriVicini(Integer strada);

	public Pastore[] chiediPastori();

	public Integer[] chiediRecintiIniziali();

	public Integer[] chiediRecintiFinali();

	public String chiediGiocatoreDiTurno();

	public Tessera[] chiediTessereTerritoriConfinanti(Integer strada);

	public Tessera[] chiediTessere();

	public Tessera[] chiediTessereInCima();

	public Giocatore[] chiediGiocatori();
}
