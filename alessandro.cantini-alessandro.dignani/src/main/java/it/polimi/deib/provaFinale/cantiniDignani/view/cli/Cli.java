package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.daA;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.listaDiInteri;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.listaDiStringhe;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.menuDiScelta;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.UtilitaStringhe.nelTerr;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
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

public class Cli implements InterfacciaUtente {
	private final InputCli in;
	private final PrintStream out = CostantiCli.DEFAULT_OUTPUT;

	public Cli(InputStream is) {
		in = new InputCli(is);
	}

	public Cli() {
		this(CostantiCli.DEFAULT_INPUT);
	}

	public Coppia<String, String> chiediNomeEPassword() {
		pulisci();
		out.println("Inserisci il tuo nome");
		String nome = in.leggiStringa();
		out.println("Inserisci la password");
		String password = in.leggiStringa();

		return Coppia.creaCoppia(nome, password);
	}

	public void nomeGiaPresente() {
		out.println("Questo nome e' gia' registrato, inseriscine un altro");
	}

	public void inizioPartita() {
		out.println("Partita iniziata con i giocatori:");
		out.println(listaDiStringhe(MainClient.getDatiPartita().getNomiGiocatori(), "; ", "."));
		TipoTerritorio tipoTesseraIniziale = MainClient.getDatiPartita().getGiocatore(nome()).getTessere().get(0).getTipo();
		out.println("La tua tessera iniziale e' di tipo " + tipoTesseraIniziale);
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
		out.println(giocatore + " ha spostato " + pecora.nomeGenerico + " " + daA(origine, destinazione));
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		out.println("La pecora nera si e' spostata " + daA(origine, destinazione, "."));
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		String tipoRecinto = MainClient.getDatiPartita().getRecinti().length > Costanti.NUM_RECINTI_INIZIALI ? "finale" : "iniziale";
		out.println(giocatore + " ha spostato il pastore " + daA(origine, destinazione) + " .");
		out.println("E' stato posizionato un recinto " + tipoRecinto + " " + nelTerr(origine));
	}

	public void movimentoLupo(int origine, int destinazione) {
		out.println("Il lupo si e' spostato " + daA(origine, destinazione, "."));
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		out.println("Il giocatore ha acquistato una tessera di tipo " + tessera.getTipo().toString());
	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? " ha abbattuto " : " non e' riuscito ad abbattere ";
		out.println(giocatore + verbo + tipo.nomeGenerico + " " + nelTerr(territorio, "."));
	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? "ha fatto" : "non e' riuscito a far";
		out.println("Il giocatore " + verbo + " accoppiare una pecora e un montone nel territorio " + nelTerr(territorio, "."));
	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		TipoAnimale adulto = maschio ? TipoAnimale.MONTONE : TipoAnimale.PECORA;
		out.println("Un agnello e' diventato " + adulto.nomeGenerico + " " + nelTerr(territorio, "."));
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		out.println(pagante + " ha pagato a " + pagato + " " + denaro + " denari.");
	}

	public void uccisioneLupo(int territorio, TipoAnimale tipoOvino) {
		out.println("Il lupo ha ucciso " + tipoOvino.nomeGenerico + " " + nelTerr(territorio));
	}

	public void finePartita(Map<String, Integer> punteggio) {
		out.println("La partita e' finita!");
		for (String gioc : punteggio.keySet()) {
			out.println(gioc + ": " + punteggio.get(gioc) + " punti");
		}
		String vincitore = ""; // TODO implementare il vincitore
		out.println("Ha vinto " + vincitore);
	}

	public void disconnessioneGiocatore(String giocatore) {
		out.println(giocatore + "si è disconnesso");
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

	private void pulisci() {
		// per ora, nullo
		for (int i = 0; i < 0; i++) {
			out.println();
		}
	}

	private int selezionaIndicePecora(List<Coppia<Integer, TipoAnimale>> pecore) {
		Iterator<Coppia<Integer, TipoAnimale>> iter = pecore.iterator();
		int numRiga = 1;
		while (iter.hasNext()) {
			Coppia<Integer, TipoAnimale> coppia = iter.next();
			out.print(numRiga + ") " + coppia.secondo + " che ora si trova " + nelTerr(coppia.primo));
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

}
