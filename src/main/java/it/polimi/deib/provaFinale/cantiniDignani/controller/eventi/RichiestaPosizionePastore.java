package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class RichiestaPosizionePastore extends Richiesta {
	private static final long serialVersionUID = -3895425016270580277L;
	boolean[] stradeLibereGratis, stradeLibereAPagamento;

	public RichiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento) {
		this.stradeLibereGratis = stradeLibereGratis.clone();
		this.stradeLibereAPagamento = stradeLibereAPagamento.clone();
	}

	@Override
	protected int interagisci() {
		return MainClient.getUI().richiestaPosizionePastore(stradeLibereGratis, stradeLibereAPagamento);
	}

}
