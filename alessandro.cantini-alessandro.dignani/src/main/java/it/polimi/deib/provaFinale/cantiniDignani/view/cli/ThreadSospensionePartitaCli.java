package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import java.io.PrintStream;

import it.polimi.deib.provaFinale.cantiniDignani.view.ThreadSospensionePartita;

public class ThreadSospensionePartitaCli extends ThreadSospensionePartita {

	private PrintStream out;
	
	public ThreadSospensionePartitaCli(String giocatore, PrintStream out) {
		super(giocatore);
		this.out = out;
	}

	@Override
	protected void visualizzaMessaggio(int secondo) {
		out.println(secondo + "...");
	}

}
