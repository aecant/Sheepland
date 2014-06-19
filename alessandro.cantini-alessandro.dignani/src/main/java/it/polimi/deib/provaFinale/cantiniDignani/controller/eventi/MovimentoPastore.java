package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

public class MovimentoPastore extends Mossa {
	private static final long serialVersionUID = -8968808141497412233L;
	
	private int origine, destinazione;
	private Giocatore[] giocDaAggiornare;
	private Integer[] recintiDaAggiornare;

	public MovimentoPastore(String giocatore, int origine, int destinazione, Giocatore[] giocDaAggiornare, Integer[] recintiDaAggiornare) {
		super(giocatore);
		this.origine = origine;
		this.destinazione = destinazione;
		this.giocDaAggiornare = giocDaAggiornare;
		this.recintiDaAggiornare = recintiDaAggiornare;
	}

	@Override
	public void aggiornaDati() {
		MainClient.getDatiPartita().setGiocatori(giocDaAggiornare);
		MainClient.getDatiPartita().setRecinti(recintiDaAggiornare);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().movimentoPastore(giocatore, origine, destinazione);
	}

}
