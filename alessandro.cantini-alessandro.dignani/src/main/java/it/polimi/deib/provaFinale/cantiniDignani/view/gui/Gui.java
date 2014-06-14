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

	/**
	 * Metodo che avvia la partita
	 */
	public void inizioPartita(DatiPartita dati) {
		finestraPartita = new PartitaView(dati);

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

		for (int i = 0; i < dati.getGiocatori().length; i++) {
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
	}

	/**
	 * Metodo che restituisce la finestra che contiene a sua volta tutte le
	 * componenti della partita
	 * 
	 * @param
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
		// System.out.println(gui.chiediNome());

		// TODO questo è un test, andrà mofificato con
		// ClientMain.getDatiPartita()
		Partita part = new Partita(Arrays.asList("Alessandro", "Andrea", "Luca", "Paolo"));
		GestorePartita gest = new GestorePartita(part, null, null);
		PreparazionePartita fi = new PreparazionePartita(gest);
		fi.disponiPecore();
		fi.disponiTessereIniziali();
		fi.distribuisciDenari();
		DatiPartita dati = Estrattore.datiPartita(part);
		// Fine test

		gui.inizioPartita(dati);

		Point[][] coordinate = new Point[Costanti.NUM_TERRITORI][5];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < 5; j++) {
				coordinate[i][j] = new Point((int) (CostantiGui.COORDINATE_TERRITORI[i][j].getX() * CostantiGui.FATTORE_DI_SCALA),
						(int) (CostantiGui.COORDINATE_TERRITORI[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}

		gui.inizioTurno(dati.getGiocatoreDiTurno());
	}
}
