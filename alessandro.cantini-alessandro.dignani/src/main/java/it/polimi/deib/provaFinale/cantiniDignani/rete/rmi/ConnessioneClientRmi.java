package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnessioneClientRmi implements InterfacciaConnessioneClient, AscoltatoreEventiRmi {

	private Registry registry;
	private InterfacciaRmi server;
	private AscoltatoreEventiRmi ascoltatore;
	private GestoreCoda<Evento> gestoreEventi = ClientMain.getGestoreEventi();

	public void inizia() {
		try {
			registry = LocateRegistry.getRegistry(CostantiRete.INDIRIZZO_SERVER, CostantiRete.PORTA_SERVER);
			server = (InterfacciaRmi) registry.lookup(CostantiRete.NOME_SERVER);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registraGiocatore(String nome) throws NomeGiaPresenteException {
		try {
			server.registraGiocatore(nome);
			ascoltatore = (AscoltatoreEventiRmi) UnicastRemoteObject.exportObject(this, 0);
			server.aggiungiAscoltatore(nome, ascoltatore);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void riceviEvento(Evento e) {
		gestoreEventi.aggiungi(e);
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

	public void termina() {
		try {
			UnicastRemoteObject.unexportObject(ascoltatore, true);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
