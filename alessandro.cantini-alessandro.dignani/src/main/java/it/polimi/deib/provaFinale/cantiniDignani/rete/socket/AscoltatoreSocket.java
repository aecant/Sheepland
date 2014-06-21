package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che permette di creare un ascoltatore in un nuovo thread.
 * 
 * @param <T>
 *            il tipo degli elementi che si vogliono ascoltare
 */
public class AscoltatoreSocket<T extends Serializable> extends Thread {

	private final static Logger logger = Logger.getLogger(AscoltatoreSocket.class.getName());

	protected ObjectInputStream in;
	protected GestoreCoda<T> coda;
	protected boolean on;

	public AscoltatoreSocket(ObjectInputStream in, GestoreCoda<T> coda) {
		this.in = in;
		this.coda = coda;
		on = true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (on) {
			T elemento = null;
			try {
				elemento = (T) in.readObject();
				if (elemento == null) {
					throw new IOError(null);
				}
				coda.aggiungi(elemento);
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE, "Errore nel tipo dell'oggetto ricevuto : " + elemento, e);
			} catch (IOException e) {
				gestisciInterruzione(e);
			}

		}
	}

	protected void gestisciInterruzione(IOException e) {
		// TODO gestire meglio eccezione
		e.printStackTrace();
	}

	public void ferma() {
		this.on = false;
	}

}
