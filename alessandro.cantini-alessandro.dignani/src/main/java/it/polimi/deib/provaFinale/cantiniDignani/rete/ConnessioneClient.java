package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

public abstract class ConnessioneClient {
	public final String indirizzoServer;
	public final GestoreCoda<Evento> codaEventi;
	
	public ConnessioneClient(String indirizzoServer, GestoreCoda<Evento> codaEventi) {
		this.indirizzoServer = indirizzoServer;
		this.codaEventi = codaEventi;
	}
	
}
