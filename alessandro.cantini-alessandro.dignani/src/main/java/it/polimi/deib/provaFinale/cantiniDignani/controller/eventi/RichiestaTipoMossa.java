package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;

import java.util.Set;

public class RichiestaTipoMossa extends Richiesta {

	private static final long serialVersionUID = -1955175358557235321L;

	private Set<TipoMossa> mosseDisponibili;

	public RichiestaTipoMossa(Set<TipoMossa> set) {
		this.mosseDisponibili = set;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaTipoMossa(mosseDisponibili);
	}

}
