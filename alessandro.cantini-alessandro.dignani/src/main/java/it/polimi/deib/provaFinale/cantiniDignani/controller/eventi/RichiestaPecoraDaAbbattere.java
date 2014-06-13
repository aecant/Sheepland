package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Collection;

public class RichiestaPecoraDaAbbattere extends RichiestaPecora {

	private static final long serialVersionUID = 5784350879771586522L;

	public RichiestaPecoraDaAbbattere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		super(t1, oviniT1, t2, oviniT2);
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPecoraDaAbbattere(getT1(), getOviniT1(), getT2(), getOviniT2());
	}

}
