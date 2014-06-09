package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.Arrays;
import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.PedinaSuStrada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

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

	public static DatiTerritorio[] datiTerritori(Partita partita) {
		DatiTerritorio[] dati = new DatiTerritorio[Costanti.NUM_TERRITORI];
		for (int i = 0; i < dati.length; i++) {
			dati[i] = new DatiTerritorio();
		}

		for (Pecora pec : partita.getGregge().getPecore()) {
			dati[pec.getPosizione().getCodice()].aggiungi(pec.getTipoOvino());
		}

		dati[partita.getGregge().getPecoraNera().getPosizione().getCodice()].aggiungi(TipoAnimale.PECORA_NERA);
		dati[partita.getLupo().getPosizione().getCodice()].aggiungi(TipoAnimale.LUPO);

		return dati;
	}

	public static Pastore[] pastori(Partita partita) {
		return (Pastore[]) partita.getPastori().toArray();
	}

	public static Integer[] recintiIniziali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiIniziali());
	}

	public static Integer[] recintiFinali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiFinali());
	}

	public static String giocatoreDiTurno(Partita partita) {
		return partita.getGiocatoreDiTurno().getNome();
	}

	private static Integer[] arrayDiPosizioni(List<PedinaSuStrada> lista) {
		int numPedine = lista.size();
		Integer[] posizioni = new Integer[numPedine];
		for (int i = 0; i < numPedine; i++) {
			posizioni[i] = lista.get(i).getStrada().getCodice();
		}
		return posizioni;
	}

	/**
	 * Restituisce un array di boolean che rappresenta le strade libere.
	 * L'indice dell'arry e' il codice della strada.
	 * 
	 * @param partita
	 *            la partita da esaminare
	 * @return array di boolean, i true sono le strade libere
	 */
	public static boolean[] stradeLibere(Partita partita) {
		boolean[] strade = new boolean[Costanti.NUM_STRADE];
		Arrays.fill(strade, true);

		for (PedinaSuStrada p : partita.getTutteLePedineSuStrada()) {
			strade[p.getStrada().getCodice()] = false;
		}

		return strade;
	}

	public static boolean[] stradeLibereAPagamento(Partita partita, Strada posPastore) {
		boolean[] strade = stradeLibere(partita);

		for (int i = 0; i < strade.length; i++) {
			if (strade[i] && Mappa.getMappa().sonoContigue(posPastore, Mappa.getMappa().getStrade()[i])) {
				strade[i] = false;
			}
		}

		return strade;
	}

	public static boolean[] stradeLibereGratis(Partita partita, Strada posPastore) {
		boolean[] stradeLibere = stradeLibere(partita);
		boolean[] stradeLibereAPagamento = stradeLibereAPagamento(partita, posPastore);
		boolean[] stradeLibereGratis = new boolean[Costanti.NUM_STRADE];

		for (int i = 0; i < stradeLibereGratis.length; i++) {
			stradeLibereGratis[i] = stradeLibere[i] && !stradeLibereAPagamento[i];
		}

		return stradeLibereGratis;
	}

	/**
	 * Restituisce una pecora che si trova su un certo territorio e che e' di un
	 * certo @see {TipoOvino}
	 * 
	 * @return la pecora su un territorio e di un certo tipo
	 */
	public static Pecora getPecora(Partita partita, int codTerritorio, TipoAnimale tipo) {
		for (Pecora pec : partita.getGregge().getPecore()) {
			if (pec.getPosizione().getCodice() == codTerritorio && pec.getTipoOvino() == tipo) {
				return pec;
			}
		}

		throw new IllegalArgumentException("La pecora non esiste");
	}

}
