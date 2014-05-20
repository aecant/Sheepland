package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.Connessione;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.DatiPartita;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.view.InterfacciaUtente;



public class Client {

	private static Mossa[] mosseDisponibili;
	private static DatiPartita datiPartita;
	private static Connessione connessione;
	private static InterfacciaUtente utente;
	private static Mossa mossaScelta;
	private static Evento eventoCorrente;
	private static String nome;
	
	public static void main(String[] args) {		
		connessione = chiediTipoConnessione();
		utente = chiediTipoInterfaccia();
		
		registraGiocatore();
		
		datiPartita = scaricaDatiPartita();
		
		
		
		while(true) {
			eventoCorrente = connessione.chiediEvento();
			utente.visualizzaEvento(eventoCorrente);
			
			if(eventoCorrente instanceof InizioTurno && ((InizioTurno) eventoCorrente).getGiocatore().getNome().equals(nome)) {
				mosseDisponibili = connessione.chiediMosseDisponibili();
				mossaScelta = utente.chiediMossa(mosseDisponibili);
			}
		}
		
	}

	private static void registraGiocatore() {
		
		//while(connessione.registraNome(utente.chiediNome()));
	}

	private static DatiPartita scaricaDatiPartita() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Connessione chiediTipoConnessione() {
		// TODO Auto-generated method stub
		return null;
	}

	private static InterfacciaUtente chiediTipoInterfaccia() {
		// TODO Auto-generated method stub
		return null;
	}

}
