package it.polimi.deib.provaFinale.cantiniDignani.view.gui;


public class Gui {
	private FinestraChiediNome fcn;
	private PartitaView finestraPartita;
	
	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * tramite una nuova finestra
	 * 
	 * @return una String con il nome
	 */
	public String chiediNome() {
		fcn = new FinestraChiediNome(this);
		return fcn.riceviNome();
	}
	
	public void inizioPartita() {
		
	}
	
	
	//Test
	public static void main (String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.chiediNome());
		System.out.println(gui.chiediNome());
		System.out.println(gui.chiediNome());
		System.out.println(gui.chiediNome());
	}
}
