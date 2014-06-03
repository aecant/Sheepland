package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

import java.util.concurrent.LinkedBlockingQueue;


public class GestioneEventi {

	private static LinkedBlockingQueue<Evento> codaEventi = new LinkedBlockingQueue<Evento>();

	/**
	 * Aspetta e restituisce un evento inviato dal server
	 * 
	 * @return l'evento ricevuto dal server
	 */
	public static Evento aspettaEvento() {
		Evento evento = null;
		try {
			evento = codaEventi.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return evento;
	}

	/**
	 * Aspetta e restituisce un evento di un determinato tipo, controlla che
	 * l'evento ricevuto sia del tipo passato come parametro, in caso negativo
	 * fa terminare il programma
	 * 
	 * @param classe
	 *            il tipo di evento che ci si aspetta
	 * @return l'evento ricevuto dal server
	 */
	public static Evento aspettaEvento(Class<?> classe) {
		Evento e = aspettaEvento();
		if (e.getClass() != classe) {
			throw new RuntimeException("L'evento è diverso da quello aspettato");
		}
		return e;
	}

	public static void aggiungi(Evento e) {
		codaEventi.add(e);
	}

}
