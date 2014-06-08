package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Set;

public abstract class RichiestaPecora extends Richiesta {

	private static final long serialVersionUID = -561851180399299704L;

	private int t1, t2;
	private Set<TipoAnimale> oviniT1, oviniT2;

	public RichiestaPecora(int t1, Set<TipoAnimale> oviniT1, int t2, Set<TipoAnimale> oviniT2) {
		this.t1 = t1;
		this.t2 = t2;
		this.oviniT1 = oviniT1;
		this.oviniT2 = oviniT2;
	}

	protected int getT1() {
		return t1;
	}

	protected int getT2() {
		return t2;
	}

	protected Set<TipoAnimale> getOviniT1() {
		return oviniT1;
	}

	protected Set<TipoAnimale> getOviniT2() {
		return oviniT2;
	}

	@Override
	abstract protected Mossa interagisci();

}
