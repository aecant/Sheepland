package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

import java.util.Set;

public abstract class RichiestaPecora extends Richiesta {

	private static final long serialVersionUID = -561851180399299704L;

	private int t1, t2;
	private Set<TipoOvino> oviniT1, oviniT2;

	public RichiestaPecora(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2) {
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

	protected Set<TipoOvino> getOviniT1() {
		return oviniT1;
	}

	protected Set<TipoOvino> getOviniT2() {
		return oviniT2;
	}

	@Override
	abstract protected Mossa interagisci();

}
