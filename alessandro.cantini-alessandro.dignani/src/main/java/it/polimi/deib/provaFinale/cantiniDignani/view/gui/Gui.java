package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gui implements InterfacciaUtente {
	
	private static final Logger LOGGER = Logger.getLogger(Gui.class.getName());
	
	private static PartitaView finestraPartita;
	private static GestoreCoda<Integer> coda = new GestoreCoda<Integer>();

	private boolean messNomeGiaPresente = false;
	private boolean messPwdSbagliata = false;

	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * tramite una nuova finestra
	 * 
	 * @return una String con il nome
	 */
	public Coppia<String, String> chiediNomeEPassword() {
		FinestraChiediNome fcn = new FinestraChiediNome(messNomeGiaPresente, messPwdSbagliata);
		return fcn.riceviNome();
	}

	/**
	 * Implementazione del metodo che avvisa che il nome scelto in precedenza
	 * non è valido
	 * 
	 */
	public void nomeGiaPresente() {
		messNomeGiaPresente = true;
		messPwdSbagliata = false;
	}

	/**
	 * Metodo che avvisa l'utente che la password scelta è sbagliata
	 */
	public void passwordSbagliata() {
		messPwdSbagliata = true;
		messNomeGiaPresente = false;
	}

	/**
	 * Metodo che avvia la partita
	 */
	public void inizioPartita(boolean riconnessione) {

		finestraPartita = new PartitaView(MainClient.getDatiPartita(), riconnessione);

		disegnaStatoTerritori();

		finestraPartita.visualizza();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "interruzione anomala nel timer", e);
		}
		
		// Se non è una riconnessione mostro la tessera iniziale
		if(!riconnessione) {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio("La tua tessera iniziale è di tipo " + MainClient.getDatiPartita().getGiocatore(MainClient.getNome()).getTessere().get(0).getTipo().toString());
		}
	}

	private void disegnaStatoTerritori() {
		for (int i = 0; i < CostantiModel.NUM_TERRITORI; i++) {
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
		if (giocatore.equals(MainClient.getNome())) {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il tuo turno!");
		} else {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il turno di " + giocatore + "!");
		}
		finestraPartita.getPanelGiocatori().impostaGiocatoreCorrente(giocatore);
	}

	public static PartitaView getFinestraPartita() {
		return finestraPartita;
	}

	public void lancioDado(Integer numero, MotivoLancioDado motivo) {
		finestraPartita.getPanelMessaggi().visualizzaLancioDado(numero, motivo);
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		finestraPartita.getMappa().creaPastore(strada, MainClient.getDatiPartita().getGiocatore(giocatore).getPastori().get(0).getColore());
	}

	public void movimentoPecora(String giocatore, TipoAnimale tipoOvino, int origine, int destinazione) {
		getPartita().getMappa().movimentoPecora(tipoOvino, origine, destinazione);
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		getFinestraPartita().getMappa().movimentoPecoraNera(origine, destinazione);
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		finestraPartita.getMappa().movimentoPastore(origine, destinazione);
		finestraPartita.getPanelGiocatori().aggiorna(giocatore);
	}

	public void movimentoLupo(int origine, int destinazione) {
		finestraPartita.getMappa().movimentoLupo(origine, destinazione);
	}

	public void finePartita(Map<String, Integer> punteggio) {
		String messaggio = "<html>La partita e' finita!<br />";
		String vincitore = null;
		int max = 0;
		for (String gioc : punteggio.keySet()) {
			messaggio += gioc + ": " + punteggio.get(gioc) + " punti<br />";
			if (punteggio.get(gioc) == max) {
				vincitore += " e " + gioc;
			} else if (punteggio.get(gioc) > max) {
				vincitore = gioc;
				max = punteggio.get(gioc);
			}
		}
		messaggio += "<br />Ha vinto " + vincitore + " con un punteggio di " + max + "!</html>";
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 60*1000);
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		if (giocatore.equals(MainClient.getNome())) {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio("Hai comprato una tessera di tipo " + tessera.getTipo());
		} else {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio("" + giocatore + " ha comprato una tessera di tipo " + tessera.getTipo());
		}

		finestraPartita.getPanelGiocatori().aggiorna(giocatore);

		// TODO Migliorare con visualizzazione dell'immagine
		finestraPartita.getPanelTessere().aggiornaTessere();
	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		if (aBuonFine) {
			finestraPartita.getPanelMessaggi().visualizzaAbbattimentoRiuscito();
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		} else {
			finestraPartita.getPanelMessaggi().visualizzaAbbattimentoFallito();
		}
	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {

		if (aBuonFine) {
			finestraPartita.getPanelMessaggi().visualizzaAccoppiamentoRiuscito();
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		} else {
			finestraPartita.getPanelMessaggi().visualizzaAccoppiamentoFallito();
		}
	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		getFinestraPartita().getMappa().disegnaTerritorio(territorio);
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		finestraPartita.getPanelGiocatori().pagamento(denaro, pagante, pagato);
		finestraPartita.getPanelTessere().aggiornaTessere();
	}

	public void uccisioneLupo(int territorio, TipoAnimale tipoOvino) {
		getFinestraPartita().getMappa().disegnaTerritorio(territorio);
	}

	public int richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		finestraPartita.getMappa().inserisciSegnaliniIniziali(stradeLibere);
		return coda.aspetta();
	}

	public int richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento) {
		finestraPartita.getMappa().inserisciSegnalini(stradeLibereGratis, stradeLibereAPagamento);
		return coda.aspetta();
	}

	public int richiestaPastore() {
		finestraPartita.getMappa().aggiungiSegnaliniPastori();
		return coda.aspetta();
	}

	public int richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili) {
		finestraPartita.getMappa().aggiungiAscoltatoriTerritori(territoriDisponibili);
		// TODO da migliorare
		return coda.aspetta();
	}

	public int richiestaTipoMossa(List<TipoMossa> mosseDisponibili, int numMossa) {
		finestraPartita.getPanelMosse().abilitaMosse(mosseDisponibili);
		// TODO Visualizzare anche il numero di mossa corrente
		return coda.aspetta();
	}

	public int richiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> oviniSpostabili) {
		finestraPartita.getMappa().aggiungiAscoltatoriAnimali(oviniSpostabili);
		// TODO Far illuminare i territori disponibili
		return coda.aspetta();
	}

	public int richiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> oviniAbbattibili) {
		finestraPartita.getMappa().aggiungiAscoltatoriAnimali(oviniAbbattibili);
		// TODO Far illuminare i territori disponibili
		return coda.aspetta();
	}

	public int richiestaTesseraDaAcquistare(List<Tessera> tessereDisponibili) {
		finestraPartita.getPanelTessereDaAcquistare().sceltaTessera(tessereDisponibili);
		return coda.aspetta();
	}

	public void disconnessioneGiocatore(String giocatore) {
		finestraPartita.getPanelMessaggi().visualizzaMessaggioDisconnessione(giocatore);
	}

	public static GestoreCoda<Integer> getCoda() {
		return coda;
	}

	public void riconnessioneGiocatore(String giocatore) {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(giocatore + " si è riconnesso");
	}

	public void saltoTurno(String giocatore) {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(giocatore + " ha saltato il turno");
	}

	public int marketRichiestaTesseraDaVendere(List<TipoTerritorio> listaTerritori) {
		finestraPartita.getPanelTessereDaVendere().sceltaTipoTessera(listaTerritori);
		return coda.aspetta();
	}

	public int marketRichiestaPrezzo(TipoTerritorio t) {
		finestraPartita.getPanelTessereDaVendere().sceltaPrezzo(t);
		return coda.aspetta();
	}

	public void marketMessaInVendita(TesseraInVendita tess) {
		String messaggio = tess.getGiocatore() + " ha messo in vendita una tessera di tipo " + tess.getTipo() + " a " + tess.getPrezzo() + " denar";
		if (tess.getPrezzo() == 1) {
			messaggio += "o";
		} else {
			messaggio += "i";
		}
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 1000);
	}

	public int marketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		finestraPartita.getPanelTessereDaVendere().marketRichiestaTesseraDaAcquistare(tessereDisponibili);
		return coda.aspetta();
	}

	public void marketCompravenditaTessera(String compratore, TesseraInVendita tesseraInVendita) {
		String messaggio = compratore + " ha acquistato una tessera di tipo " + tesseraInVendita.getTipo() + " da " + tesseraInVendita.getGiocatore();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 2000);
	}

	public void marketInizio() {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("Inizio fase vendita market");
	}

	public void marketInizioAcquisti() {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("Inizio fase acquisti market");
	}
}
