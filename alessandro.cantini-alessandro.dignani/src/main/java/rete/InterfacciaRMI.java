package rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Tessera;

public interface InterfacciaRMI extends Remote{
	
	public Tessera[] getTessereConfinanti(String giocatore, int strada) throws RemoteException;
	
	public void registraGiocatore(String nome) throws RemoteException;

}
