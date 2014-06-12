package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
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
	private String nome;

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
			this.nome = nome;
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

	public void inviaMossa(Mossa mossaScelta) {
		try {
			server.riceviMossa(mossaScelta);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatiTerritorio[] chiediElencoTerritori() {
		DatiTerritorio[] dati = null;
		try {
			dati = server.chiediElencoTerritori(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dati;
	}

	public Giocatore[] chiediGiocatori() {
		Giocatore[] giocatori = null;
		try {
			giocatori = server.chiediGiocatori(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return giocatori;
	}

	public Pastore[] chiediPastori() {
		// TODO Auto-generated method stub
		Pastore[] pastori = null;
		try {
			pastori = server.chiediPastori(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pastori;
	}

	public Integer[] chiediRecintiIniziali() {
		Integer[] recintiIniziali = null;
		try {
			recintiIniziali = server.chiediRecintiIniziali(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recintiIniziali;
	}

	public Integer[] chiediRecintiFinali() {
		Integer[] recintiFinali = null;
		try {
			recintiFinali = server.chiediRecintiFinali(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recintiFinali;
	}

	public String chiediGiocatoreDiTurno() {
		String giocatoreDiTurno = null;
		try {
			giocatoreDiTurno = server.chiediGiocatoreDiTurno(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return giocatoreDiTurno;
	}

	public Tessera[] chiediTessereInCima() {
		Tessera[] tessere = null;
		try {
			tessere = server.chiediTessereInCima(nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tessere;
	}

}
