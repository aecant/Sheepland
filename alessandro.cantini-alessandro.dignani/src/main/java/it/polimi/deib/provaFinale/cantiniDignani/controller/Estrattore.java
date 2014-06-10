package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.PedinaSuStrada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.util.Arrays;
import java.util.List;

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

	/**
	 * Restituisce un array di DatiTerritorio. Ogni elemento dell'array contiene
	 * i dati su un territorio, l'indice dell'array corrisponde al codice del
	 * territorio.
	 * 
	 * @param partita
	 *            la partita di cui si vogliono i dati
	 * @return un array di DatiTerritorio che descrive lo stato dei territori di
	 *         una partita
	 */
	public static DatiTerritorio[] datiTerritori(Partita partita) {
		DatiTerritorio[] dati = new DatiTerritorio[Costanti.NUM_TERRITORI];
		for (int i = 0; i < dati.length; i++) {
			dati[i] = new DatiTerritorio();
		}

		for (Pecora pec : partita.getGregge().getPecore()) {
			dati[pec.getPosizione().getCodice()].aggiungi(pec.getTipoAnimale());
		}

		dati[partita.getGregge().getPecoraNera().getPosizione().getCodice()].aggiungi(TipoAnimale.PECORA_NERA);
		dati[partita.getLupo().getPosizione().getCodice()].aggiungi(TipoAnimale.LUPO);

		return dati;
	}

	/**
	 * Restituisce l'array dei giocatori di una certa partita
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere i giocatori
	 * @return l'array dei giocatori di una partita
	 */
	public static Giocatore[] giocatori(Partita partita) {
		return partita.getGiocatori().toArray(new Giocatore[partita.getGiocatori().size()]);
	}

	/**
	 * Restituisce l'array dei pastori di una certa partita
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere i pastori
	 * @return l'array dei pastori di una partita
	 */
	public static Pastore[] pastori(Partita partita) {
		return partita.getPastori().toArray(new Pastore[partita.getPastori().size()]);
	}

	/**
	 * Restituisc l'array delle posizioni dei recinti iniziali.
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere i recinti iniziali
	 * @return l'array delle posizioni dei recinti iniziali
	 */
	public static Integer[] recintiIniziali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiIniziali());
	}

	/**
	 * Restituisc l'array delle posizioni dei recinti finale.
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere i recinti finali
	 * @return l'array delle posizioni dei recinti finali
	 */
	public static Integer[] recintiFinali(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getRecintiFinali());
	}

	/**
	 * Restituisce il giocatore che sta giocando il proprio turno
	 * 
	 * @param partita
	 *            la partita in cui si gioca
	 * @return il giocatore di turno
	 */
	public static String giocatoreDiTurno(Partita partita) {
		return partita.getGiocatoreDiTurno().getNome();
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

	/**
	 * Restituisce un array di boolean. L'indice dell'array corrisponde al
	 * codice della strada, gli elementi valgono true se la strada
	 * corrispondente e' libera e non e' contigua alla strada passata come
	 * parametro.
	 * 
	 * @param partita
	 *            la partita in interesse
	 * @param posPastore
	 *            la posizione del pastore
	 * @return un array di boolean che indica le strade libere in cui un pastore
	 *         si puo' spostare pagando.
	 */
	public static boolean[] stradeLibereAPagamento(Partita partita, Strada posPastore) {
		boolean[] strade = stradeLibere(partita);

		for (int i = 0; i < strade.length; i++) {
			if (strade[i] && Mappa.getMappa().sonoContigue(posPastore, Mappa.getMappa().getStrade()[i])) {
				strade[i] = false;
			}
		}

		return strade;
	}

	/**
	 * Restituisce un array di boolean. L'indice dell'array corrisponde al
	 * codice della strada, gli elementi valgono true se la strada
	 * corrispondente e' libera ed e' contigua alla strada passata come
	 * parametro.
	 * 
	 * @param partita
	 *            la partita in interesse
	 * @param posPastore
	 *            la posizione del pastore
	 * @return un array di boolean che indica le strade libere in cui un pastore
	 *         si puo' spostare gratis.
	 */
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
	 * certo tipo
	 * 
	 * @return la pecora su un territorio e di un certo tipo
	 */
	public static Pecora getPecora(Partita partita, int codTerritorio, TipoAnimale tipo) {

		for (Pecora pec : partita.getGregge().getPecore()) {
			if (pec.getPosizione().getCodice() == codTerritorio && pec.getTipoAnimale() == tipo) {
				return pec;
			}
		}

		throw new IllegalArgumentException("La pecora non esiste");
	}

	/**
	 * Restituisce l'array delle tessere in cima al mazzo
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere le tessere
	 * @return l'array delle tessere in cima al mazzo
	 */
	public static Tessera[] tessereInCima(Partita partita) {
		Tessera[] tessere = new Tessera[TipoTerritorio.valoriTessere().length];
		for (int i = 0; i < tessere.length; i++) {
			tessere[i] = partita.getMazzo().leggiTesseraInCima(TipoTerritorio.valoriTessere()[i]);
		}

		return tessere;
	}

	/**
	 * Data una lista di pedine su strada, restituisc un array contente i codici
	 * delle strade su cui stanno le pedine della lista
	 * 
	 * @param lista
	 *            la lista di pedine su strada
	 * @return l'array delle posizioni delle pedine
	 */
	private static Integer[] arrayDiPosizioni(List<PedinaSuStrada> lista) {
		int numPedine = lista.size();
		Integer[] posizioni = new Integer[numPedine];
		for (int i = 0; i < numPedine; i++) {
			posizioni[i] = lista.get(i).getStrada().getCodice();
		}
		return posizioni;
	}
}
