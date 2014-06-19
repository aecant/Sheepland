package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

import java.util.List;

public class RichiestaPecoraDaAbbattere extends RichiestaPecora {

	private static final long serialVersionUID = 5784350879771586522L;

	public RichiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> ovini) {
		super(ovini);
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaPecoraDaAbbattere(ovini);
	}

}
