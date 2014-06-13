package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AscoltatoreRemoto extends Remote {

	void riceviEvento (Evento e) throws RemoteException;

}
