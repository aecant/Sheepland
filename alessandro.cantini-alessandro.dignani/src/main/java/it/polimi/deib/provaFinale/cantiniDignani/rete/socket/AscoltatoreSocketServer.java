package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;

import java.io.IOException;
import java.io.ObjectInputStream;

public class AscoltatoreSocketServer extends AscoltatoreSocket<Integer> {

	private Utente utente;
	
	public AscoltatoreSocketServer(ObjectInputStream in, Utente utente) {
		super(in, utente.getCodaMosse());
		this.utente = utente;
	}
	
	@Override
	protected void gestisciInterruzione(IOException e) {
		utente.setOnline(false);
	}

}
