package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnessioneClientRmi implements ConnessioneClient, AscoltatoreRemoto {

	private Registry registry;
	private InterfacciaRmi server;

	public void inizializza() {
		try {
			registry = LocateRegistry.getRegistry(CostantiRete.SERVER_ADDRESS, CostantiRete.SERVER_PORT);
			server = (InterfacciaRmi) registry.lookup(CostantiRete.SERVER_NAME);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registraGiocatore(String nome) {
		try {
			server.registraGiocatore(nome);
			AscoltatoreRemoto ascoltatore = (AscoltatoreRemoto) UnicastRemoteObject.exportObject(this, 0);
			server.aggiungiAscoltatore(nome, ascoltatore);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void riceviEvento(Evento e) {
		ClientMain.getGestoreEventi().aggiungi(e);
	}

	public DatiPartita scaricaDatiPartita() {
		DatiPartita dati = null;
		try {
			dati = server.scaricaDatiPartita(ClientMain.getNome());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dati;
	}

	public void inviaMossa(int mossaScelta) {
		try {
			server.riceviMossa(mossaScelta);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
