package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;


public abstract class Mossa implements Evento {
	private static final long serialVersionUID = 1032317303850055693L;
	
	protected String giocatore;

	public Mossa(String giocatore) {
		this.giocatore = giocatore;
	}

	public abstract void aggiornaDati();

	public abstract void visualizza();

}
