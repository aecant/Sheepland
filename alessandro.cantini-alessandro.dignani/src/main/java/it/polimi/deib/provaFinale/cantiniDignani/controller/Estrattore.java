package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mazzo;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mazzo.MazzoFinitoException;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.PedinaSuStrada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che fornisce metodi statici per effettuare query e ottenere dati dal
 * model
 */
public class Estrattore {

	private static final Logger LOGGER = Logger.getLogger(Estrattore.class.getName());

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
		DatiTerritorio[] dati = new DatiTerritorio[CostantiModel.NUM_TERRITORI];
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
	 * Restituisc l'array delle posizioni dei recinti iniziali.
	 * 
	 * @param partita
	 *            la partita di cui si vogliono conoscere i recinti iniziali
	 * @return l'array delle posizioni dei recinti iniziali
	 */
	public static Integer[] recinti(Partita partita) {
		return arrayDiPosizioni(partita.getRecinti().getListaRecinti());
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
		boolean[] strade = new boolean[CostantiModel.NUM_STRADE];
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
		boolean[] stradeLibereGratis = new boolean[CostantiModel.NUM_STRADE];

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

		throw new IllegalArgumentException("Non esiste " + tipo.getNomeGenerico() + " nel territorio " + codTerritorio);
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
			try {
				tessere[i] = partita.getMazzo().leggiTesseraInCima(TipoTerritorio.valoriTessere()[i]);
			} catch (MazzoFinitoException e) {
				LOGGER.log(Level.FINE, "il mazzo e' finito", e);
				tessere[i] = Mazzo.TESSERA_FINITA;
			}
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

	/**
	 * Restituisce un istanza di DatiPartita contenente i dati sulla partita
	 * passata come parametro
	 * 
	 * @param partita
	 *            la partita di cui si vogliono avere i dati
	 * @return un istanza di DatiPartita
	 */
	public static DatiPartita datiPartita(Partita partita) {
		return new DatiPartita(partita);
	}

	/**
	 * Restituisce un agnello che diventera' randomicamente pecora o montone
	 * 
	 * @param t
	 *            il territorio su cui creare l'agnello
	 * @return un agnello di sesso casuale
	 */
	public static Agnello agnelloRandom(Territorio t) {
		return new Agnello(t, Sorte.lanciaMoneta());
	}

	/**
	 * Restituisce un ovino che e' randomicamente una pecora, un montone o un
	 * agnello. Nel caso in cui sia agnello il sesso viene stabilito lanciando
	 * una moneta.
	 * 
	 * @param t
	 *            il territorrio su cui creare l'ovino
	 * @return una pecora creata randomicamente
	 */
	public static Pecora pecoraRandom(Territorio t) {
		switch (Sorte.numeroCasuale(1, 3)) {
		case 1:
			return new Pecora(t, true);
		case 2:
			return new Pecora(t, false);
		default:
			return agnelloRandom(t);
		}

	}

	/**
	 * Costruttore privato per nascondere quello di default
	 */
	private Estrattore() {
	}
}
