package it.polimi.deib.provaFinale.cantiniDignani.controller;

/**
 * Eccezione che serve a segnalare violazioni delle regole della partita
 */
public class SheeplandException extends RuntimeException {

	private static final long serialVersionUID = -4377029614520741820L;

	public SheeplandException(String message) {
		super(message);
	}

	public SheeplandException(Throwable cause) {
		super(cause);
	}

	public SheeplandException(String message, Throwable cause) {
		super(message, cause);
	}

}
