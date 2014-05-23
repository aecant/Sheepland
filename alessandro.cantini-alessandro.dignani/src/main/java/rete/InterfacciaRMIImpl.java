package rete;

import java.rmi.*;

import model.Mappa;
import model.Partita;
import model.Territorio;
import model.Tessera;

public class InterfacciaRMIImpl implements InterfacciaRMI {

	public Tessera[] getTessereConfinanti(int codPartita, int strada) throws RemoteException {
		// TODO da cambiare
		Partita partita = new Partita();
		Tessera[] tessere = new Tessera[2];
		Territorio t1, t2;
		t1 = Mappa.getMappa().getStrade()[strada].getTerritorio1();
		t2 = Mappa.getMappa().getStrade()[strada].getTerritorio2();

		tessere[0] = partita.getMazzo().leggiTesseraInCima(t1.getTipo());
		tessere[1] = partita.getMazzo().leggiTesseraInCima(t2.getTipo());

		return tessere;
	}

	public Integer numero() throws RemoteException {
		return 2;
	}

}
