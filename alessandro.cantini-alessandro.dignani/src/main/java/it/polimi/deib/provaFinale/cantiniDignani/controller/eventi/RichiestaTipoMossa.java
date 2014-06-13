package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.util.ArrayList;
import java.util.Collection;

public class RichiestaTipoMossa extends Richiesta {

	private static final long serialVersionUID = -1955175358557235321L;

	private ArrayList<TipoMossa> mosseDisponibili;
	private int numMossa;

	public RichiestaTipoMossa(Collection<TipoMossa> mosseDisp, int numMossa) {
		this.mosseDisponibili = (ArrayList<TipoMossa>) mosseDisp;
		this.numMossa = numMossa;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTipoMossa(mosseDisponibili, numMossa);
	}

}
