package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.List;

public class RichiestaPecoraDaMuovere extends RichiestaPecora {
	private static final long serialVersionUID = 8050350177901857718L;

	public RichiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> ovini) {
		super(ovini);
	}

	@Override
	protected int interagisci() {
		return ClientMain.getUI().richiestaPecoraDaMuovere(ovini);
	}

}
