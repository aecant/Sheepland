package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Collection;

public class RichiestaPecoraDaMuovere extends RichiestaPecora {
	private static final long serialVersionUID = 8050350177901857718L;

	public RichiestaPecoraDaMuovere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		super(t1, oviniT1, t2, oviniT2);
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPecoraDaMuovere(getT1(), getOviniT1(), getT2(), getOviniT2());
	}

}
