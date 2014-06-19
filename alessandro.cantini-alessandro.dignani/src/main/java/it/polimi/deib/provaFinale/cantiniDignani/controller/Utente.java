package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import com.sun.istack.internal.logging.Logger;

public class Utente {
	private static final Logger logger = Logger.getLogger(Utente.class);

	private final String nome;
	private final String password;
	private final GestoreCoda<Integer> codaMosse;
	private InterfacciaConnessioneServer connessione;

	public Utente(String nome, String password, InterfacciaConnessioneServer connessione) {
		this.nome = nome;
		this.password = password;
		this.connessione = connessione;
		codaMosse = new GestoreCoda<Integer>();
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

	public GestoreCoda<Integer> getCodaMosse() {
		return codaMosse;
	}

	public void inviaEvento(Evento evento) {
		connessione.inviaEvento(evento, this);
		logger.info(evento + " inviato " + " a " + this);
	}

	public void setConnessione(InterfacciaConnessioneServer connessione) {
		this.connessione = connessione;
	}

	public InterfacciaConnessioneServer getConnessione() {
		return connessione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Utente)) {
			return false;
		}
		Utente other = (Utente) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", password=" + password + ", connessione=" + connessione + "]";
	}

}
