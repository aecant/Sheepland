package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	void registraGiocatore(String giocatore) throws RemoteException;

	void aggiungiAscoltatore(String giocatore, AscoltatoreRemoto ascoltatore) throws RemoteException;

	void riceviMossa(Mossa mossa) throws RemoteException;

	DatiPartita scaricaDatiPartita(String giocatore) throws RemoteException;

	DatiTerritorio[] chiediElencoTerritori(String giocatore) throws RemoteException;

	Giocatore[] chiediGiocatori(String giocatore) throws RemoteException;

	Integer[] chiediRecintiIniziali(String giocatore) throws RemoteException;

	Integer[] chiediRecintiFinali(String giocatore) throws RemoteException;

	String chiediGiocatoreDiTurno(String giocatore) throws RemoteException;

	Tessera[] chiediTessereInCima(String giocatore) throws RemoteException;

}
