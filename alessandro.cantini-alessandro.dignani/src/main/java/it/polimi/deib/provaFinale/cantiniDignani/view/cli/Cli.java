package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.daA;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.listaDiInteri;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.listaDiStringhe;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.menuDiScelta;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.nelTerr;
import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli implements InterfacciaUtente {
	
	private static final String DENARI = " denari";

	private static final Logger LOGGER = Logger.getLogger(Cli.class.getName());
	
	private final InputCli in;
	private final PrintStream out;

	public Cli(InputStream is) {
		in = new InputCli(is);
		out = CostantiCli.DEFAULT_OUTPUT;
	}

	public Cli() {
		this(CostantiCli.DEFAULT_INPUT);
	}

	public Coppia<String, String> chiediNomeEPassword() {
		out.println("Inserisci il tuo nome");
		String nome = in.leggiStringa();
		out.println("Inserisci la password");
		String password = in.leggiStringa();

		return Coppia.creaCoppia(nome, password);
	}

	public void nomeGiaPresente() {
		out.println("Questo nome e' gia' registrato, inseriscine un altro");
	}

	public void passwordSbagliata() {
		out.println("La password inserita e' sbagliata");
	}

	public void inizioPartita(boolean riconnessione) {
		if (riconnessione) {
			out.println("Ti sei riconnesso alla partita con i giocatori:");
		} else {
			out.println("Partita iniziata con i giocatori:");
		}
		out.println(listaDiStringhe(MainClient.getDatiPartita().getNomiGiocatori(), "; ", "."));

		if (!riconnessione) {
			TipoTerritorio tipoTesseraIniziale = MainClient.getDatiPartita().getGiocatore(nome()).getTessere().get(0).getTipo();
			out.println("La tua tessera iniziale e' di tipo " + tipoTesseraIniziale);
		}
		stampaStatoTerritori();
	}

	public void inizioTurno(String giocatore) {
		if (giocatore.equals(nome())) {
			out.println("E' il tuo turno.");
		} else {
			out.println("E' il turno di " + giocatore);
		}
	}

	public void lancioDado(Integer numero, MotivoLancioDado motivo) {
		out.println("Dado lanciato: " + numero + " per il motivo: " + motivo);
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		out.println(giocatore + " ha posizionato il pastore sulla strada " + strada + " .");
	}

	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione) {
		out.println(giocatore + " ha spostato " + pecora.getNomeGenerico() + " " + daA(origine, destinazione));
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		out.println("La pecora nera si e' spostata " + daA(origine, destinazione, "."));
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		String tipoRecinto = MainClient.getDatiPartita().getRecinti().length > CostantiModel.NUM_RECINTI_INIZIALI ? "finale" : "iniziale";
		out.println(giocatore + " ha spostato il pastore " + daA(origine, destinazione) + " .");
		out.println("E' stato posizionato un recinto " + tipoRecinto + " " + nelTerr(origine));
	}

	public void movimentoLupo(int origine, int destinazione) {
		out.println("Il lupo si e' spostato " + daA(origine, destinazione, "."));
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		out.println("Il giocatore ha acquistato una tessera di tipo " + tessera.getTipo().toString());
		if (giocatore.equals(nome())) {
			out.println("Possiedi le seguenti tessere: ");
			out.println(MainClient.getDatiPartita().getGiocatore(nome()).numeroTesserePerTipo());
		}
	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? " ha abbattuto " : " non e' riuscito ad abbattere ";
		out.println(giocatore + verbo + tipo.getNomeGenerico() + " " + nelTerr(territorio, "."));
	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? "ha fatto" : "non e' riuscito a far";
		out.println("Il giocatore " + verbo + " accoppiare una pecora e un montone nel territorio " + nelTerr(territorio, "."));
	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		TipoAnimale adulto = maschio ? TipoAnimale.MONTONE : TipoAnimale.PECORA;
		out.println("Un agnello e' diventato " + adulto.getNomeGenerico() + " " + nelTerr(territorio, "."));
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		out.println(pagante + " ha pagato a " + pagato + " " + denaro + DENARI);
	}

	public void uccisioneLupo(int territorio, TipoAnimale tipoOvino) {
		out.println("Il lupo ha ucciso " + tipoOvino.getNomeGenerico() + " " + nelTerr(territorio));
	}

	public void finePartita(Map<String, Integer> punteggio) {
		out.println("La partita e' finita!");
		
		int max = 0;
		String vincitore = "";
		for (String gioc : punteggio.keySet()) {
			int punti = punteggio.get(gioc);
			out.println(gioc + ": " + punti + " punti");
			if(punti == max) {
				vincitore += ", " + gioc;
			} else if (punti > max){
				max = punti;
				vincitore = gioc;
			}
		}
		
		out.println("Ha vinto " + vincitore);
	}

	public void disconnessioneGiocatore(String giocatore) {
		out.println(giocatore + " si e' disconnesso");
		out.println("La partita verra' sospesa per " + CostantiController.SECONDI_INTERRUZIONE_DISCONNESSIONE + " secondi per permettere a " + giocatore + " di riconnettersi");
		for(int i = CostantiController.SECONDI_INTERRUZIONE_DISCONNESSIONE; i >= 0; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "interruzione anomala nella visualizzazione del timer", e);
			}
			
			out.print(i + "...");
		}
		out.println("\nLa partita puo' riprendere");
	}

	public void riconnessioneGiocatore(String giocatore) {
		out.println(giocatore + " si e' riconnesso");
	}

	public void saltoTurno(String giocatore) {
		out.println(giocatore + " ha saltato il turno");

	}

	public int richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		List<Integer> stradeDisponibili = Utilita.indiciTrue(stradeLibere);
		out.println("Inserisci la posizione iniziale del pastore");
		out.println("Le strade disponibili sono le seguenti: ");
		out.println(listaDiInteri(stradeDisponibili));

		return in.leggiIntero(stradeDisponibili);
	}

	public int richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento) {
		List<Integer> gratis = Utilita.indiciTrue(stradeLibereGratis);
		List<Integer> pagamento = Utilita.indiciTrue(stradeLibereAPagamento);
		out.println("Inserisci la posizione in cui vuoi muovere il pastore");
		out.println("Queste sono le strade gratis");
		out.println(listaDiInteri(gratis));
		out.println("Queste sono le strade a pagamento");
		out.println(listaDiInteri(pagamento));

		List<Integer> tutte = new ArrayList<Integer>();
		tutte.addAll(pagamento);
		tutte.addAll(gratis);

		return in.leggiIntero(tutte);
	}

	public int richiestaPastore() {
		List<Pastore> pastori = MainClient.getDatiPartita().getGiocatore(nome()).getPastori();
		out.println("Devi scegliere uno dei tuoi pastori");
		out.println(menuDiScelta(pastori));
		Pastore scelto = in.scegliElemento(pastori);

		return scelto.getStrada().getCodice();
	}

	public int richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili) {
		out.println("Devi selezionare un territorio in cui far accoppiare una pecora e un montone");
		out.println(menuDiScelta(territoriDisponibili));

		return in.scegliElemento(territoriDisponibili);
	}

	public int richiestaTipoMossa(List<TipoMossa> mosseDisponibili, int numMossa) {
		out.println("Devi effettuare la mossa numero " + numMossa + ".");
		out.println("Scegli la mossa da effettuare fra le seguenti:");
		out.println(menuDiScelta(mosseDisponibili));

		return scegliIndice(mosseDisponibili);
	}

	public int richiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> oviniSpostabili) {
		out.println("Devi selezionare una pecora da spostare");

		return selezionaIndicePecora(oviniSpostabili);
	}

	public int richiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> oviniAbbattibili) {
		out.println("Devi selezionare una pecora da abbattere");

		return selezionaIndicePecora(oviniAbbattibili);
	}

	public int richiestaTesseraDaAcquistare(List<Tessera> tessereDisp) {
		out.println("Devi selezionare una tessera da acquistare");
		out.println(menuDiScelta(tessereDisp));

		return scegliIndice(tessereDisp);

	}

	private int selezionaIndicePecora(List<Coppia<Integer, TipoAnimale>> pecore) {
		Iterator<Coppia<Integer, TipoAnimale>> iter = pecore.iterator();
		int numRiga = 1;
		while (iter.hasNext()) {
			Coppia<Integer, TipoAnimale> coppia = iter.next();
			out.print(numRiga + ") " + coppia.getSecondo() + " che ora si trova " + nelTerr(coppia.getPrimo()));
			out.println(iter.hasNext() ? ";" : ".");
			numRiga++;
		}
		return scegliIndice(pecore);
	}

	private void stampaStatoTerritori() {
		out.println("La situazione dei territori e' questa");
		DatiTerritorio[] dati = MainClient.getDatiPartita().getTerritori();

		for (int i = 0; i < dati.length; i++) {
			String animali = "";

			for (TipoAnimale t : dati[i].getTipiAnimale()) {
				animali += t + "(x" + dati[i].getNum(t) + ")";
			}

			out.println(i + ") " + animali + ";");
		}
	}

	private int scegliIndice(Collection<?> mosseDisponibili) {
		return in.leggiIntero(1, mosseDisponibili.size()) - 1;
	}

	private String nome() {
		return MainClient.getNome();
	}

	public void marketCompravenditaTessera(String compratore, TesseraInVendita tess) {
		out.println(compratore + " ha acquistato una tessera di tipo " + tess.getTipo() + " da " + tess.getGiocatore() + " pagando " + tess.getPrezzo() + DENARI);
	}

	public void marketInizio() {
		out.println("E' iniziata la fase del market");
	}

	public void marketInizioAcquisti() {
		out.println("E' iniziata la fase del market in cui si possono acquistare le carte");
	}

	public void marketMessaInVendita(TesseraInVendita tess) {
		out.println(tess.getGiocatore() + " ha messo in vendita una tessera di tipo " + tess.getTipo() + " al prezzo di " + tess.getPrezzo() + DENARI);
	}

	public int marketRichiestaPrezzo(TipoTerritorio tipo) {
		out.println("Inserisci il prezzo a cui vuoi vendere la tessera di tipo " + tipo);
		return in.leggiIntero(CostantiController.MIN_PREZZO_MARKET, 4);
	}

	public int marketRichiestaTesseraDaAcquistare(List<TesseraInVendita> tessereDisponibili) {
		out.println("Devi scegliere una tessera da acquistare fra le seguenti, 0 per saltare");
		out.println(menuDiScelta(tessereDisponibili));
		int scelta = in.leggiIntero(0, tessereDisponibili.size());
		if (scelta == 0) {
			return CostantiController.TERMINATORE_MARKET;
		}
		return scelta - 1;
	}

	public int marketRichiestaTesseraDaVendere(List<TipoTerritorio> territoriDisponibili) {
		out.println("Devi scegliere una tessera da vendere fra quelle coi seguenti tipi territorio, 0 per saltare");
		out.println(menuDiScelta(territoriDisponibili));
		int scelta = in.leggiIntero(0, territoriDisponibili.size());
		if (scelta == 0) {
			return CostantiController.TERMINATORE_MARKET;
		}
		return scelta - 1;
	}

}
