package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.FaseIniziale;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;

import java.awt.Point;
import java.util.Arrays;


public class Gui {
	private FinestraChiediNome fcn;
	public PecoraView pec; // TEMPORANEO per il test
	
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
		
		// TODO questo è un test, andrà mofificato con ClientMain.getDatiPartita()
		Partita part = new Partita(Arrays.asList("Alessandro", "Andrea", "Luca", "Paolo"));
		GestorePartita gest = new GestorePartita(part, null, null);
		FaseIniziale fi = new FaseIniziale(gest);
		fi.disponiPecore();
		fi.disponiTessereIniziali();
		fi.distribuisciDenari();
		DatiPartita dati = new DatiPartita(part);
		// Fine test

		PartitaView partita = new PartitaView(dati);
		
		pec = partita.getMappa().getPec();
		partita.visualizza();
	}
	
	
	//Test
	public static void main (String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.chiediNome());
		gui.inizioPartita();
		
		while(true) {
			gui.pec.muoviPecora(new Point(Sorte.numeroCasuale(0, CostantiGui.dimensioneMappa.width), Sorte.numeroCasuale(0, CostantiGui.dimensioneMappa.height)));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
