package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.util.List;

public class RichiestaTipoMossa extends Richiesta {

	private static final long serialVersionUID = -1955175358557235321L;

	private List<TipoMossa> mosseDisponibili;
	private int numMossa;

	public RichiestaTipoMossa(List<TipoMossa> mosseDisp, int numMossa) {
		this.mosseDisponibili = mosseDisp;
		this.numMossa = numMossa;
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaTipoMossa(mosseDisponibili, numMossa);
	}

}
