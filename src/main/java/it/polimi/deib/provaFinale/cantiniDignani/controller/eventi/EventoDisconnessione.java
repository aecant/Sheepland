package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

public abstract class EventoDisconnessione implements Evento {
	private static final long serialVersionUID = 6988332445783450334L;

	
	public final void aggiornaDati() {
		// non ci sono dati da aggiornare
	}

	public abstract void visualizza();

}
