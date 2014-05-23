package controller.eventi;


public abstract class Mossa implements Evento {
	private static final long serialVersionUID = 1032317303850055693L;
	private String giocatore;

	public Mossa(String giocatore) {
		this.giocatore = giocatore;
	}

	public String getGiocatore() {
		return giocatore;
	}

	public abstract void aggiornaDati();

	public abstract void visualizza();

}
