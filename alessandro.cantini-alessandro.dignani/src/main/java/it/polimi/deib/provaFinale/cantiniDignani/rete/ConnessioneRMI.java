package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConnessioneRMI implements ConnessioneClient {
	
	private Registry registry;
	InterfacciaRMI server;
	
	public void inizializza() {
		try {
			registry = LocateRegistry.getRegistry(Costanti.SERVER_ADDRESS, Costanti.SERVER_PORT);
			server = (InterfacciaRMI) registry.lookup(Costanti.SERVER_NAME);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registraGiocatore(String nome) throws RemoteException {
		server.registraGiocatore(nome);
	}

	public Evento chiediEvento() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mossa[] chiediMosseDisponibili() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inviaMossa(Mossa mossaScelta) {
		// TODO Auto-generated method stub
		
	}

	public DatiTerritorio[] chiediElencoTerritori() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer[] chiediStradeConfinanti(Integer strada) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer[] chiediStradeLibere() {
		// TODO Auto-generated method stub
		return null;
	}

	public DatiTerritorio[] chediPecoreTerritoriVicini(Integer strada) {
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

	public Tessera[] chiediTessereTerritoriConfinanti(Integer strada) {
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

	public Giocatore[] chiediGiocatori() {
		// TODO Auto-generated method stub
		return null;
	}

	public DatiPartita scaricaDatiPartita() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
