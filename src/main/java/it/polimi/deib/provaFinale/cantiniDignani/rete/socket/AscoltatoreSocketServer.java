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
		if(utente == null || in == null || serverSheepland == null) {
			throw new IllegalArgumentException();
		}
		this.utente = utente;
		this.serverSheepland = serverSheepland;
	}
	
	@Override
	protected void gestisciInterruzione(IOException e) {
		serverSheepland.gestisciDisconnessione(utente);
		ferma();
	}

}
