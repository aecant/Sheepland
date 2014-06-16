package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

public class Gui implements InterfacciaUtente{
	private FinestraChiediNome fcn;
	static PartitaView finestraPartita;
	boolean messErrore = false;

	// Tempornei
	static public Partita part;
	static public GestorePartita gest;

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
		// TODO questo è un test, andrà mofificato con
		// ClientMain.getDatiPartita()
//		part = new Partita(Arrays.asList("Dig", "Andre", "Luca", "Cant"));
//		gest = new GestorePartita(part, null, null);
//		PreparazionePartita fi = new PreparazionePartita(gest);
//		fi.disponiPecore();
//		fi.disponiTessereIniziali();
//		fi.distribuisciDenari();
		

		finestraPartita = new PartitaView(getDati());

		Point[][] coordinateTerritori = new Point[Costanti.NUM_TERRITORI][5];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < 5; j++) {
				coordinateTerritori[i][j] = new Point((int) (CostantiGui.COORDINATE_TERRITORI[i][j].getX() * CostantiGui.FATTORE_DI_SCALA),
						(int) (CostantiGui.COORDINATE_TERRITORI[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}

		Point[] coordinateStrade = new Point[Costanti.NUM_STRADE];
		for (int i = 0; i < Costanti.NUM_STRADE; i++) {
			coordinateStrade[i] = new Point((int) (CostantiGui.COORDINATE_STRADE[i].getX() * CostantiGui.FATTORE_DI_SCALA),
					(int) (CostantiGui.COORDINATE_STRADE[i].getY() * CostantiGui.FATTORE_DI_SCALA));
		}

		// Creo le pedine dei giocatori
		// TODO da modificare
		for (int i = 0; i < getDati().getGiocatori().length; i++) {
			finestraPartita.getMappa().creaPastore(coordinateStrade[Sorte.numeroCasuale(0, Costanti.NUM_STRADE - 1)], ColorePastore.values()[i]);
		}

		disegnaStatoTerritori();
		
		finestraPartita.visualizza();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("La tua tessera iniziale è di tipo " + Gui.getDati().getGiocatore(Gui.getNome()).getTessere().get(0).getTipo().toString());
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
		getPartita().getPanelMessaggi().visualizzaMessaggio("E' il turno di " + giocatore + "!");
	}

	// Test
//	public static void main(String[] args) {
//		Gui gui = new Gui();
//
//		gui.inizioPartita();
//
//		gui.inizioTurno(getDati().getGiocatoreDiTurno());
//	}

	static DatiPartita getDati() {

		return ClientMain.getDatiPartita();
	}

	protected static String getNome() {
		return ClientMain.getNome();
	}

	public static PartitaView getFinestraPartita() {
		return finestraPartita;
	}

	public void lancioDado(Integer numero, MotivoLancioDado motivo) {
		// TODO Auto-generated method stub
		
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		// TODO Auto-generated method stub
		
	}

	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione) {
		// TODO Auto-generated method stub
		
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
