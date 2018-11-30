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
import it.polimi.deib.provaFinale.cantiniDignani.view.ThreadSospensionePartita;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che implementa tutti i metodi di interfaccia utente in modo grafico
 * @author alessandrodignani
 *
 */
public class Gui implements InterfacciaUtente {

	private static final Logger LOGGER = Logger.getLogger(Gui.class.getName());

	private static PartitaView finestraPartita;
	private static GestoreCoda<Integer> coda = new GestoreCoda<Integer>();

	private boolean messNomeGiaPresente = false;
	private boolean messPwdSbagliata = false;

	private ThreadSospensionePartita sospensionePartita;

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
		if (!riconnessione) {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio(
					"La tua tessera iniziale è di tipo " + MainClient.getDatiPartita().getGiocatore(MainClient.getNome()).getTessere().get(0).getTipo().toString());
		}
	}

	/**
	 * Metodo che disegna gli animali conenuti in ogni territorio della mappa
	 */
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

	/**
	 * Metodo che visualizza il giocatore di turno
	 */
	public void inizioTurno(String giocatore) {
		if (giocatore.equals(MainClient.getNome())) {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il tuo turno!");
		} else {
			getPartita().getPanelMessaggi().visualizzaMessaggio("E' il turno di " + giocatore + "!");
		}
		finestraPartita.getPanelGiocatori().impostaGiocatoreCorrente(giocatore);
	}

	/**
	 * getter della finestra della partita che contiene tutto
	 * @return
	 */
	public static PartitaView getFinestraPartita() {
		return finestraPartita;
	}

	/**
	 * Metodo che visualizza un lancio del dado con il relativo motivo
	 */
	public void lancioDado(Integer numero, MotivoLancioDado motivo) {
		finestraPartita.getPanelMessaggi().visualizzaLancioDado(numero, motivo);
	}

	/**
	 * metodo che visualizza la scelta della posizione iniziale di un pastore
	 * scelta da un giocatore
	 */
	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		finestraPartita.getMappa().creaPastore(strada, MainClient.getDatiPartita().getGiocatore(giocatore).getPastori().get(0).getColore());
	}

	/**
	 * Metodo che visualizza il movimento di una pecora da un territorio ad un altro
	 */
	public void movimentoPecora(String giocatore, TipoAnimale tipoOvino, int origine, int destinazione) {
		getPartita().getMappa().movimentoPecora(tipoOvino, origine, destinazione);
	}

	/**
	 * Metodo che visualizza il movimento della pecora nera
	 */
	public void movimentoPecoraNera(int origine, int destinazione) {
		getFinestraPartita().getMappa().movimentoPecoraNera(origine, destinazione);
	}

	/**
	 * Metodo che visualizza il movimento di un pastore da una strada ad un'altra
	 */
	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		finestraPartita.getMappa().movimentoPastore(origine, destinazione);
		finestraPartita.getPanelGiocatori().aggiorna(giocatore);
	}

	/**
	 * Metodo che visualizza il movimento del lupo
	 */
	public void movimentoLupo(int origine, int destinazione) {
		finestraPartita.getMappa().movimentoLupo(origine, destinazione);
	}

	/**
	 * Metodo che visualizza il risultato finale della partita
	 * con i relativi punteggi e il vincitore della partita
	 */
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
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 60 * 1000);
	}

	/**
	 * Metodo che visualizza l'acquisto di una tessera
	 */
	public void acquistoTessera(String giocatore, Tessera tessera) {
		if (giocatore.equals(MainClient.getNome())) {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio("Hai comprato una tessera di tipo " + tessera.getTipo());
		} else {
			finestraPartita.getPanelMessaggi().visualizzaMessaggio("" + giocatore + " ha comprato una tessera di tipo " + tessera.getTipo());
		}

		finestraPartita.getPanelGiocatori().aggiorna(giocatore);

		finestraPartita.getPanelTessere().aggiornaTessere();
	}

	/**
	 * Metodo che visualizza l'abbattimento di un animale
	 */
	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		if (aBuonFine) {
			finestraPartita.getPanelMessaggi().visualizzaAbbattimentoRiuscito();
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		} else {
			finestraPartita.getPanelMessaggi().visualizzaAbbattimentoFallito();
		}
	}

	/**
	 * Metodo che visualizza l'accoppiamento
	 */
	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {

		if (aBuonFine) {
			finestraPartita.getPanelMessaggi().visualizzaAccoppiamentoRiuscito();
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		} else {
			finestraPartita.getPanelMessaggi().visualizzaAccoppiamentoFallito();
		}
	}

	/**
	 * Metodo che visualizza la trasformazione di un agnello in pecora o montone
	 */
	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		getFinestraPartita().getMappa().disegnaTerritorio(territorio);
	}
	
	/**
	 * Metodo che visualizza un pagamento
	 */
	public void pagamento(Integer denaro, String pagante, String pagato) {
		finestraPartita.getPanelGiocatori().pagamento(denaro, pagante, pagato);
		finestraPartita.getPanelTessere().aggiornaTessere();
	}

	/**
	 * Metodo che visualizza quando un lupo mangia un ovino
	 */
	public void uccisioneLupo(int territorio, TipoAnimale tipoOvino) {
		getFinestraPartita().getMappa().disegnaTerritorio(territorio);
	}

	/**
	 * Metodo che richiede all'utente la posizione in cui vuole posizionare il pastore inizialmente 
	 */
	public int richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		finestraPartita.getMappa().inserisciSegnaliniIniziali(stradeLibere);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede all'utente la posizione in cui vuole spostare il pastore
	 */
	public int richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento) {
		finestraPartita.getMappa().inserisciSegnalini(stradeLibereGratis, stradeLibereAPagamento);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede quale pastore si vuole utilizzare dei 2 (nella partita a 2 giocatori)
	 */
	public int richiestaPastore() {
		finestraPartita.getMappa().aggiungiSegnaliniPastori();
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede su quale territorio si vuole eseguire l'accoppiamento
	 */
	public int richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili) {
		finestraPartita.getMappa().aggiungiAscoltatoriTerritori(territoriDisponibili);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede quale tipo di mossa si vuole effettuare
	 */
	public int richiestaTipoMossa(List<TipoMossa> mosseDisponibili, int numMossa) {
		finestraPartita.getPanelMosse().abilitaMosse(mosseDisponibili);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede quale pecora si vuole muovere
	 */
	public int richiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> oviniSpostabili) {
		finestraPartita.getMappa().aggiungiAscoltatoriAnimali(oviniSpostabili);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede quale pecora si vuole abbattere
	 */
	public int richiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> oviniAbbattibili) {
		finestraPartita.getMappa().aggiungiAscoltatoriAnimali(oviniAbbattibili);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede quale tessera si vuole acquistare
	 */
	public int richiestaTesseraDaAcquistare(List<Tessera> tessereDisponibili) {
		finestraPartita.getPanelTessereDaAcquistare().sceltaTessera(tessereDisponibili);
		return coda.aspetta();
	}

	/**
	 * Metodo che visualizza quando un giocatore si è disconnesso
	 */
	public void disconnessioneGiocatore(String giocatore) {
		sospensionePartita = new ThreadSospensionePartitaGui(giocatore);
		sospensionePartita.start();
	}

	/**
	 * Metodo che visualizza quando un giocatore si è riconnesso
	 */
	public void riconnessioneGiocatore(String giocatore) {
		sospensionePartita.ferma();
		try {
			sospensionePartita.join();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "interruzione inaspettata sospensione partita", e);
		}
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(giocatore + " si e' riconnesso");
	}

	/**
	 * Metodo che visualizza quando un giocatore ha saltato il turno
	 */
	public void saltoTurno(String giocatore) {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(giocatore + " ha saltato il turno");
	}

	/**
	 * Metodo che richiede quale tessera si vuole vendere
	 */
	public int marketRichiestaTesseraDaVendere(List<TipoTerritorio> listaTerritori) {
		finestraPartita.getPanelTessereDaVendere().sceltaTipoTessera(listaTerritori);
		return coda.aspetta();
	}

	/**
	 * Metodo che richiede a quale prezzo si vuole vendere la tessera
	 */
	public int marketRichiestaPrezzo(TipoTerritorio t) {
		finestraPartita.getPanelTessereDaVendere().sceltaPrezzo(t);
		return coda.aspetta();
	}

	/**
	 * Metodo che visualizza quando un giocatore mette in vendita una tessera
	 */
	public void marketMessaInVendita(TesseraInVendita tess) {
		String messaggio = tess.getGiocatore() + " ha messo in vendita una tessera di tipo " + tess.getTipo() + " a " + tess.getPrezzo() + " denar";
		if (tess.getPrezzo() == 1) {
			messaggio += "o";
		} else {
			messaggio += "i";
		}
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 1000);
	}

	/**
	 * Metodo che richiede quale tessera si vuole acquistare della lista fornita
	 */
	public int marketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		finestraPartita.getPanelTessereDaVendere().marketRichiestaTesseraDaAcquistare(tessereDisponibili);
		return coda.aspetta();
	}

	/**
	 * Getter per la coda degli eventi
	 */
	public static GestoreCoda<Integer> getCoda() {
		return coda;
	}

	/**
	 * Metodo che visualizza quando un giocatore ha acquistato una tessera nel market
	 */
	public void marketCompravenditaTessera(String compratore, TesseraInVendita tesseraInVendita) {
		String messaggio = compratore + " ha acquistato una tessera di tipo " + tesseraInVendita.getTipo() + " da " + tesseraInVendita.getGiocatore();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio, 2000);
	}

	/**
	 * Metodo che visualizza l'inizio del market
	 */
	public void marketInizio() {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("Inizio fase vendita market");
	}

	/**
	 * Metodo che visualizza che sta iniziando la fase degli acquisti nel market
	 */
	public void marketInizioAcquisti() {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("Inizio fase acquisti market");
	}
}
