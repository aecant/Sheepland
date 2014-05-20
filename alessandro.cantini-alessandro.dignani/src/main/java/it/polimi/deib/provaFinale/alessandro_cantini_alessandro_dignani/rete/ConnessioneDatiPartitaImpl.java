package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mappa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Partita;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

import java.rmi.*;
import java.rmi.server.*;

public class ConnessioneDatiPartitaImpl extends UnicastRemoteObject implements ConnessioneDatiPartita{
	
	private static final long serialVersionUID = 6026732884498386977L;
	private Partita partita;
	
	
	protected ConnessioneDatiPartitaImpl(Partita partita) throws RemoteException {
		this.partita = partita;
	}

	public Tessera[] getTessereConfinanti(int strada) throws RemoteException {
		Tessera[] tessere = new Tessera[2];
		Territorio t1, t2;
		t1 = Mappa.getMappa().getStrade()[strada].getTerritorio1();
		t2 = Mappa.getMappa().getStrade()[strada].getTerritorio2();
		
		tessere[0] = partita.getMazzo().getTesseraInCima(t1.getTipo());
		tessere[1] = partita.getMazzo().getTesseraInCima(t2.getTipo());
		
		return tessere;
	}
	
}
