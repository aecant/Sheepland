package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

import java.util.Set;

public class RichiestaPecoraDaMuovere extends Richiesta {

	private static final long serialVersionUID = -561851180399299704L;

	private int t1, t2;
	private Set<TipoOvino> oviniT1, oviniT2;

	public RichiestaPecoraDaMuovere(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2) {
		this.t1 = t1;
		this.t2 = t2;
		this.oviniT1 = oviniT1;
		this.oviniT2 = oviniT2;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPecoraDaSpostare(t1, oviniT1, t2, oviniT2);
	}

}
