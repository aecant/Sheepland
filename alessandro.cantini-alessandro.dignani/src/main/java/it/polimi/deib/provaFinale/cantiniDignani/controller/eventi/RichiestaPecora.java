package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.List;

public abstract class RichiestaPecora extends Richiesta {

	private static final long serialVersionUID = -561851180399299704L;

	protected List<Coppia<Integer, TipoAnimale>> ovini;

	public RichiestaPecora(List<Coppia<Integer, TipoAnimale>> ovini) {
		this.ovini = ovini;
	}

	@Override
	abstract protected int interagisci();

}
