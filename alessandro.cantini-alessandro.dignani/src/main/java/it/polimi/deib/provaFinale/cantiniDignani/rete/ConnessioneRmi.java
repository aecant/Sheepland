package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnessioneRmi implements ConnessioneClient, AscoltatoreRemoto {

	private Registry registry;
	private InterfacciaRmi server;

	public void inizializza() {
		try {
			registry = LocateRegistry.getRegistry(Costanti.SERVER_ADDRESS, Costanti.SERVER_PORT);
			server = (InterfacciaRmi) registry.lookup(Costanti.SERVER_NAME);

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
		// TODO Auto-generated method stub
		return null;
	}

	public void inviaMossa(Mossa mossaScelta) {
		try {
			server.riceviMossa(mossaScelta);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatiTerritorio[] chiediElencoTerritori() {
		// TODO Auto-generated method stub
		return null;
	}

	public Giocatore[] chiediGiocatori() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pastore[] chiediPastori() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer[] chiediRecintiIniziali() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer[] chiediRecintiFinali() {
		// TODO Auto-generated method stub
		return null;
	}

	public String chiediGiocatoreDiTurno() {
		// TODO Auto-generated method stub
		return null;
	}

	public Tessera[] chiediTessere() {
		// TODO Auto-generated method stub
		return null;
	}

	public Tessera[] chiediTessereInCima() {
		// TODO Auto-generated method stub
		return null;
	}


}
