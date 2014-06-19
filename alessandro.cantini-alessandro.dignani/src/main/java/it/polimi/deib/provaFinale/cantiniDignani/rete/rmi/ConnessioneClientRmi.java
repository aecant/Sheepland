package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnessioneClientRmi implements InterfacciaConnessioneClient, InterfacciaAscoltatoreRmi {

	private Registry registry;
	private InterfacciaRmi server;
	private InterfacciaAscoltatoreRmi ascoltatore;
	private GestoreCoda<Evento> gestoreEventi = MainClient.getGestoreEventi();

	public void inizia() {
		try {
			registry = LocateRegistry.getRegistry(CostantiRete.INDIRIZZO_SERVER, CostantiRete.PORTA_SERVER_RMI);
			server = (InterfacciaRmi) registry.lookup(CostantiRete.NOME_SERVER_RMI);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registraGiocatore(Coppia<String, String> nomeEPassword) throws NomeGiaPresenteException, PasswordSbagliataException {
		try {
			server.registraGiocatore(nomeEPassword.primo, nomeEPassword.secondo);
			ascoltatore = (InterfacciaAscoltatoreRmi) UnicastRemoteObject.exportObject(this, 0);
			server.aggiungiAscoltatore(nomeEPassword.primo, ascoltatore);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void riceviEvento(Evento evento) {
		gestoreEventi.aggiungi(evento);
	}

	public boolean seiOnline() {
		return true;
	}

	public void inviaMossa(Integer mossaScelta) {
		try {
			server.riceviMossa(MainClient.getNome(), mossaScelta);
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
