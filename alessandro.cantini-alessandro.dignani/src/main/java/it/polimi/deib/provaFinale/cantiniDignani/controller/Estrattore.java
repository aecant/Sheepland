package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.PedinaSuStrada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;
import it.polimi.deib.provaFinale.cantiniDignani.rete.DatiTerritorio;

/**
 * Classe che fornisce metodi statici per effettuare query e ottenere dati dal
 * model
 */
public class Estrattore {

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private Estrattore() {
	}

	public static Tessera[] tessereConfinanti(Partita partita, int strada) {
		Tessera[] tessere = new Tessera[2];

		Territorio t1, t2;
		t1 = Mappa.getMappa().getStrade()[strada].getTerritorio1();
		t2 = Mappa.getMappa().getStrade()[strada].getTerritorio2();

		tessere[0] = partita.getMazzo().leggiTesseraInCima(t1.getTipo());
		tessere[1] = partita.getMazzo().leggiTesseraInCima(t2.getTipo());

		return tessere;
	}

	public static int[] stradeLibere() {
		// TODO Auto-generated method stub
		return null;
	}

	public static DatiTerritorio[] datiTerritori(Partita partita) {
		DatiTerritorio[] dati = new DatiTerritorio[Costanti.NUM_TERRITORI];
		for(int i = 0; i < dati.length; i++) {
			dati[i] = new DatiTerritorio();
		}
		
		for (Pecora pec : partita.getGregge().getPecore()) {
			dati[pec.getPosizione().getCodice()].aggiungiOvino(pec.getTipoOvino());
		}
		
		dati[partita.getGregge().getPecoraNera().getPosizione().getCodice()].aggiungiOvino(TipoOvino.PECORANERA);
		dati[partita.getLupo().getPosizione().getCodice()].aggiungiLupo();
		
		return dati;
	}
	
	public static Pastore[] pastori(Partita partita) {
		return (Pastore[]) partita.getPastori().toArray();
	}
	
	public Integer[] recintiIniziali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiIniziali());
	}
	
	public Integer[] recintiFinali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiFinali());
	}
	
	public String giocatoreDiTurno(Partita partita) {
		return partita.getGiocatoreDiTurno().getNome();
	}
	
	private Integer[] arrayDiPosizioni(List<PedinaSuStrada> lista) {
		int numPedine = lista.size();
		Integer[] posizioni = new Integer[numPedine];
		for (int i = 0; i < numPedine; i++) {
			posizioni[i] = lista.get(i).getStrada().getCodice();
		}
		return posizioni;
	}

}
