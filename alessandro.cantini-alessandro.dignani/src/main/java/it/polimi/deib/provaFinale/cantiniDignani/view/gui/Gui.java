package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Gui implements InterfacciaUtente {
	private FinestraChiediNome fcn;
	static PartitaView finestraPartita;
	boolean messErrore = false;

	// Tempornei
	static public Partita part;
	static public GestorePartita gest;

	public Gui() {
	}

	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * tramite una nuova finestra
	 * 
	 * @return una String con il nome
	 */
	public String chiediNome() {
		fcn = new FinestraChiediNome(messErrore);
		return fcn.riceviNome();
	}

	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * avvisandolo che il nome scelto in precedenza non è valido
	 * 
	 * @return String del nome scelto
	 */
	public void nomeGiaPresente() {
		messErrore = true;
	}

	/**
	 * Metodo che avvia la partita
	 */
	public void inizioPartita() {

		finestraPartita = new PartitaView(ClientMain.getDatiPartita());

		disegnaStatoTerritori();

		finestraPartita.visualizza();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(
				"La tua tessera iniziale è di tipo " + ClientMain.getDatiPartita().getGiocatore(ClientMain.getNome()).getTessere().get(0).getTipo().toString());
	}

	private void disegnaStatoTerritori() {
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			finestraPartita.getMappa().disegnaTerritorio(i);
		}
	}

	/**
	 * Metodo che restituisce la finestra che contiene a sua volta tutte le
	 * componenti della partita
	 * 
	 */
	public PartitaView getPartita() {
		return finestraPartita;
	}

	public void inizioTurno(String giocatore) {
		if (giocatore.equals(ClientMain.getNome())) {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il tuo turno!");
		} else {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il turno di " + giocatore + "!");
		}

	}

	public static PartitaView getFinestraPartita() {
		return finestraPartita;
	}

	public void lancioDado(Integer numero, MotivoLancioDado motivo) {
		finestraPartita.getPanelMessaggi().visualizzaLancioDado(numero, motivo);
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		finestraPartita.getMappa().creaPastore(strada, ClientMain.getDatiPartita().getGiocatore(giocatore).getPastori().get(0).getColore());
	}

	public void movimentoPecora(String giocatore, TipoAnimale tipoOvino, int origine, int destinazione) {
		getPartita().getMappa().movimentoPecora(tipoOvino, origine, destinazione);
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoLupo(int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void finePartita(Map<String, Integer> punteggio) {
		// TODO Auto-generated method stub
		
	}
	
	public void acquistoTessera(String giocatore, Tessera tessera) {
		// TODO Auto-generated method stub

	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		// TODO Auto-generated method stub

	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {
		// TODO Auto-generated method stub

	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		// TODO Auto-generated method stub

	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		// TODO Auto-generated method stub

	}

	public void uccisioneLupo(int territorio, TipoAnimale tipoOvino) {
		// TODO Auto-generated method stub

	}

	public int richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaPastore() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaTipoMossa(List<TipoMossa> mosseDisponibili, int numMossa) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> oviniSpostabili) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> oviniAbbattibili) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int richiestaTesseraDaAcquistare(List<Tessera> tessereDisponibili) {
		// TODO Auto-generated method stub
		return 0;
	}

}
