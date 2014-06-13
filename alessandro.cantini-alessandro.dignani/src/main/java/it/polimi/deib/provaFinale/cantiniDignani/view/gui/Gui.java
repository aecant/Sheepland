package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.FaseIniziale;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
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
	public void inizioPartita() {

		// TODO questo è un test, andrà mofificato con
		// ClientMain.getDatiPartita()
		Partita part = new Partita(Arrays.asList("Alessandro", "Andrea", "Luca", "Paolo"));
		GestorePartita gest = new GestorePartita(part, null, null);
		FaseIniziale fi = new FaseIniziale(gest);
		fi.disponiPecore();
		fi.disponiTessereIniziali();
		fi.distribuisciDenari();
		DatiPartita dati = Estrattore.datiPartita(part);
		// Fine test

		finestraPartita = new PartitaView(dati);

		Point[][] coordinate = new Point[Costanti.NUM_TERRITORI][5];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < 5; j++) {
				coordinate[i][j] = new Point((int) (CostantiGui.COORDINATE[i][j].getX() * CostantiGui.FATTORE_DI_SCALA), (int) (CostantiGui.COORDINATE[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}

		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < 5; j++) {
				finestraPartita.getMappa().creaPecora(coordinate[i][j]);
			}
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

	// Test
	public static void main(String[] args) {
		Gui gui = new Gui();
		//System.out.println(gui.chiediNome());
		gui.inizioPartita();

		Point[][] coordinate = new Point[Costanti.NUM_TERRITORI][5];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < 5; j++) {
				coordinate[i][j] = new Point((int) (CostantiGui.COORDINATE[i][j].getX() * CostantiGui.FATTORE_DI_SCALA), (int) (CostantiGui.COORDINATE[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}
		
		
		for (int i = 0; i < Costanti.NUM_TERRITORI * 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.getPartita().getMappa().getPec().get(i).muovi(coordinate[Sorte.numeroCasuale(0, Costanti.NUM_TERRITORI - 1)][Sorte.numeroCasuale(0, 4)]);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.getPartita().getMappa().getPec().get(Costanti.NUM_TERRITORI * 5 - i - 1).elimina();
		}
	}
}
