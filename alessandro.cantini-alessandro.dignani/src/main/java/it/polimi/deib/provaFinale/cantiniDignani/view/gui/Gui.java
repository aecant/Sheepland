package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.PreparazionePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;

import java.awt.Point;
import java.util.Arrays;

public class Gui {
	private FinestraChiediNome fcn;
	PartitaView finestraPartita;

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
		fcn = new FinestraChiediNome(false);
		return fcn.riceviNome();
	}

	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * avvisandolo che il nome scelto in precedenza non è valido
	 * 
	 * @return String del nome scelto
	 */
	public String nomeGiaPresente() {
		fcn = new FinestraChiediNome(true);
		return fcn.riceviNome();
	}

	/**
	 * Metodo che avvia la partita
	 */
	public void inizioPartita() {
		// TODO questo è un test, andrà mofificato con
		// ClientMain.getDatiPartita()
		part = new Partita(Arrays.asList("Dig", "Andre", "Luca", "Cant"));
		gest = new GestorePartita(part, null, null);
		PreparazionePartita fi = new PreparazionePartita(gest);
		fi.disponiPecore();
		fi.disponiTessereIniziali();
		fi.distribuisciDenari();

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

		for (int i = 0; i < getDati().getGiocatori().length; i++) {
			finestraPartita.getMappa().creaPastore(coordinateStrade[Sorte.numeroCasuale(0, Costanti.NUM_STRADE - 1)], ColorePastore.values()[i]);
		}

		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			finestraPartita.getMappa().creaPecoraNera(coordinateTerritori[i][0]);
			finestraPartita.getMappa().creaLupo(coordinateTerritori[i][1]);
			finestraPartita.getMappa().creaPecora(coordinateTerritori[i][2]);
			finestraPartita.getMappa().creaMontone(coordinateTerritori[i][3]);
			finestraPartita.getMappa().creaAgnello(coordinateTerritori[i][4]);
		}
		finestraPartita.visualizza();
		finestraPartita.getPanelMessaggi().visualizzaMessaggio("La tua tessera iniziale è di tipo " + Gui.getDati().getGiocatore(Gui.getNome()).getTessere().get(0).getTipo().toString());
	}

	/**
	 * Metodo che restituisce la finestra che contiene a sua volta tutte le
	 * componenti della partita
	 * 
	 */
	public PartitaView getPartita() {
		return this.finestraPartita;
	}

	public void inizioTurno(String giocatore) {
		getPartita().getPanelMessaggi().visualizzaMessaggio("E' il turno di " + giocatore + "!");
	}

	// Test
	public static void main(String[] args) {
		Gui gui = new Gui();

		gui.inizioPartita();

		gui.inizioTurno(getDati().getGiocatoreDiTurno());
	}

	private static DatiPartita getDati() {

		return Estrattore.datiPartita(part);
		// Fine test
	}

	protected static String getNome() {
		return "Dig";
	}
}
