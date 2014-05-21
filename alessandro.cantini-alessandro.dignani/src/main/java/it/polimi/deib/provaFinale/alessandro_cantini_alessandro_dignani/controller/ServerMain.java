package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.util.Vector;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.InterfacciaServer;


public class ServerMain {
	private static Vector<String> clientInAttesa;
	private static InterfacciaServer connessione;
	
	
	public static void main(String[] args) {
		impostaTipoConnessione();
		
	
		while(true) {
			clientInAttesa.add(connessione.clientConnesso());
			int numeroClient = clientInAttesa.size();
		/*	if(numeroClient >= 2)
				time = startTimer();*/
			if(numeroClient == 4)
				nuovaPartita(); //aggiungi partita a pool di thread)
		}
		
	}
	
	
	private static void nuovaPartita() {
		//pool di GestorePartita
	}

	private static void impostaTipoConnessione() {
		//TODO in base alla scelta iniziale, o avvio un pool di thread con i socket, o creo il registry RMI
	}
}
