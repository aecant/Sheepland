package rete;

import java.rmi.RemoteException;

public class NomeGiaPresenteException extends RemoteException {

	private static final long serialVersionUID = 7496212372080702479L;

	public NomeGiaPresenteException() {
		super();
	}

	public NomeGiaPresenteException(String s) {
		super(s);
	}

	public NomeGiaPresenteException(String s, Throwable cause) {
		super(s, cause);
	}

}
