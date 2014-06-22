package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

public abstract class ConnessioneClient {
	private final String indirizzoServer;
	private final GestoreCoda<Evento> codaEventi;

	public ConnessioneClient(String indirizzoServer, GestoreCoda<Evento> codaEventi) {
		this.indirizzoServer = indirizzoServer;
		this.codaEventi = codaEventi;
	}

	public String getIndirizzoServer() {
		return indirizzoServer;
	}

	public GestoreCoda<Evento> getCodaEventi() {
		return codaEventi;
	}

}
