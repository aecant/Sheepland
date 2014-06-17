package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

public class Utente {

	private final String nome;
	private final String password;
	private final GestoreCoda<Integer> codaMosse;
	private boolean online;

	public Utente(String nome, String password) {
		this.nome = nome;
		this.password = password;
		online = true;
		codaMosse = new GestoreCoda<Integer>();
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Integer aspettaMossa() {
		return codaMosse.aspetta();
	}

	public void aggiungiMossa(Integer elemento) {
		codaMosse.aggiungi(elemento);
	}

	public GestoreCoda<Integer> getCodaMosse() {
		return codaMosse;
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
		return "Utente [nome=" + nome + "]";
	}

}
