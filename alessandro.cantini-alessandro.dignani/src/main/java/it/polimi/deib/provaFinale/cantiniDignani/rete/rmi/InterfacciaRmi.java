package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	public void registraGiocatore(String giocatore) throws RemoteException;

	public void aggiungiAscoltatore(String giocatore, AscoltatoreRemoto ascoltatore) throws RemoteException;

	public void riceviMossa(Mossa mossa) throws RemoteException;

	public DatiPartita scaricaDatiPartita(String giocatore) throws RemoteException;

	public DatiTerritorio[] chiediElencoTerritori(String giocatore) throws RemoteException;

	public Giocatore[] chiediGiocatori(String giocatore) throws RemoteException;

	public Pastore[] chiediPastori(String giocatore) throws RemoteException;

	public Integer[] chiediRecintiIniziali(String giocatore) throws RemoteException;

	public Integer[] chiediRecintiFinali(String giocatore) throws RemoteException;

	public String chiediGiocatoreDiTurno(String giocatore) throws RemoteException;

	public Tessera[] chiediTessereInCima(String giocatore) throws RemoteException;

}
