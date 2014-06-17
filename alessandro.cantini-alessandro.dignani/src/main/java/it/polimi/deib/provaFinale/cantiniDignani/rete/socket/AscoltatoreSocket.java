package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Classe che permette di creare un ascoltatore in un nuovo thread.
 * 
 * @param <T>
 *            il tipo degli elementi che si vogliono ascoltare
 */
public class AscoltatoreSocket<T extends Serializable> extends Thread {

	private ObjectInputStream in;
	private GestoreCoda<T> coda;
	private boolean on;

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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (elemento == null) {
				throw new NullPointerException();
			}

			coda.aggiungi(elemento);

		}
	}

	public void ferma() {
		this.on = false;
	}

}
