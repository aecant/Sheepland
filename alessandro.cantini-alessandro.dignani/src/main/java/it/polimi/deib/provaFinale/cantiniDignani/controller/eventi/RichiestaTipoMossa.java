package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.util.Set;

public class RichiestaTipoMossa extends Richiesta {

	private static final long serialVersionUID = -1955175358557235321L;

	private Set<TipoMossa> mosseDisponibili;
	private int numMossa;

	public RichiestaTipoMossa(Set<TipoMossa> set, int numMossa) {
		this.mosseDisponibili = set;
		this.numMossa = numMossa;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTipoMossa(mosseDisponibili, numMossa);
	}

}
