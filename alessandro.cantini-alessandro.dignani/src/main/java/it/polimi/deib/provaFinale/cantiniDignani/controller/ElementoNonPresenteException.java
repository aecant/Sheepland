package it.polimi.deib.provaFinale.cantiniDignani.controller;

public class ElementoNonPresenteException extends RuntimeException {

	private static final long serialVersionUID = -5650894825070199334L;

	public ElementoNonPresenteException() {
		super();
	}

	public ElementoNonPresenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElementoNonPresenteException(String message) {
		super(message);
	}

	public ElementoNonPresenteException(Throwable cause) {
		super(cause);
	}

}
