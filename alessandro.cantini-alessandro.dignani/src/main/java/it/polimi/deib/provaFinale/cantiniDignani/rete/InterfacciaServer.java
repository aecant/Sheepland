package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

public interface InterfacciaServer {
	
	public void inizializza();
	
	public void inviaEventoATutti(Evento evento);

}
