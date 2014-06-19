package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Gui implements InterfacciaUtente {
	private FinestraChiediNome fcn;
	static PartitaView finestraPartita;
	boolean messErrore = false;
	private static GestoreCoda<Integer> coda = new GestoreCoda<Integer>();

	public Gui() {
	}

	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * tramite una nuova finestra
	 * 
	 * @return una String con il nome
	 */
	public Coppia<String, String> chiediNomeEPassword() {
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

		finestraPartita = new PartitaView(MainClient.getDatiPartita());

		disegnaStatoTerritori();

		finestraPartita.visualizza();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(
				"La tua tessera iniziale è di tipo " + MainClient.getDatiPartita().getGiocatore(MainClient.getNome()).getTessere().get(0).getTipo().toString());
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
		if (giocatore.equals(MainClient.getNome())) {
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
			if(punteggio.get(gioc) == max) {
				vincitore += " " + gioc;
			} else if (punteggio.get(gioc) > max) {
				vincitore = gioc;
				max = punteggio.get(gioc);
			}
		}
		messaggio += "<br />Ha vinto " + vincitore + " con un punteggio di " + max + "!</html>";
		finestraPartita.getPanelMessaggi().visualizzaMessaggio(messaggio);
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("" + giocatore + " ha comprato una tessera di tipo " + tessera.getTipo());
		// TODO Migliorare con visualizzazione dell'immagine
		finestraPartita.getPanelTessere().aggiornaTessere();
	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		if(aBuonFine) {
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		}
		// TODO da integrare con l'immagine animata dell'accoppiamento
	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {
		if(aBuonFine) {
			finestraPartita.getMappa().disegnaTerritorio(territorio);
		}
		// TODO da integrare con l'immagine animata dell'accoppiamneto
	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		getFinestraPartita().getMappa().disegnaTerritorio(territorio);
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		// TODO Auto-generated method stub
		// ####################################################################################
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
		// TODO Auto-generated method stub
		// ##################################################################################
		
		return 0;
	}

	public void disconnessioneGiocatore(String giocatore) {
		// TODO migliorare
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("Il giocatore " + giocatore + " si è disconnesso!");
	}

	public static GestoreCoda<Integer> getCoda() {
		return coda;
	}
}
