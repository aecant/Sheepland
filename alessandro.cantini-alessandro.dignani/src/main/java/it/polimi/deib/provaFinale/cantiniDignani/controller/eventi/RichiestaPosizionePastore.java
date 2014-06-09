package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

public class RichiestaPosizionePastore extends Richiesta {
	private static final long serialVersionUID = -3895425016270580277L;
	boolean[] stradeLibereGratis, stradeLibereAPagamento;
	private int origine;

	public RichiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento, int origine) {
		this.stradeLibereGratis = stradeLibereGratis;
		this.stradeLibereAPagamento = stradeLibereAPagamento;
		this.origine = origine;
	}

	@Override
	protected Mossa interagisci() {
		return ClientMain.getUI().richiestaPosizionePastore(stradeLibereGratis, stradeLibereAPagamento, origine);
	}

}
