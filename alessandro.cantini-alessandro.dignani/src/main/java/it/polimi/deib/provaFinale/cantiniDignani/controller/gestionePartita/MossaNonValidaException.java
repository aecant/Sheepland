package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

/**
 * Eccezione che segnala che il client ha risposto con una mossa non valida
 */
public class MossaNonValidaException extends RuntimeException {

	private static final long serialVersionUID = -270481645032368246L;

	public MossaNonValidaException() {
		super();
	}

	public MossaNonValidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MossaNonValidaException(String message) {
		super(message);
	}

}