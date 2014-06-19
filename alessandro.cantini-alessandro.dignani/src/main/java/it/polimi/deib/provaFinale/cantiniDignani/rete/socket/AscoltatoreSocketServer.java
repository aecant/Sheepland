package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;

import java.io.IOException;
import java.io.ObjectInputStream;

public class AscoltatoreSocketServer extends AscoltatoreSocket<Integer> {

	private final Utente utente;
	private final ServerSheepland serverSheepland;
	
	public AscoltatoreSocketServer(ObjectInputStream in, Utente utente, ServerSheepland serverSheepland) {
		super(in, utente.getCodaMosse());
		this.utente = utente;
		this.serverSheepland = serverSheepland;
	}
	
	@Override
	protected void gestisciInterruzione(IOException e) {
		serverSheepland.gestisciDisconnessione(utente);
		ferma();
	}

}
