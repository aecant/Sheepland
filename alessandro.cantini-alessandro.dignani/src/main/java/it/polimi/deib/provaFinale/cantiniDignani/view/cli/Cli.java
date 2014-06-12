package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.daA;
import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.listaDiInteri;
import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.listaDiStringhe;
import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.menuDiScelta;
import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.nelTerr;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Abbattimento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Accoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.AcquistoTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecora;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cli implements InterfacciaUtente {
	private final InputCli in;
	private final PrintStream out = CostantiCli.DEFAULT_OUTPUT;

	public Cli(InputStream is) {
		in = new InputCli(is);
	}

	public Cli() {
		this(CostantiCli.DEFAULT_INPUT);
	}

	public String chiediNome() {
		pulisci();
		out.println("Inserisci il tuo nome");
		return in.leggiStringa();
	}

	public void nomeGiaPresente() {
		out.println("Questo nome e' gia' registrato, inseriscine un altro");
	}

	public void inizioPartita() {
		out.println("Partita iniziata con i giocatori:");
		out.println(listaDiStringhe(ClientMain.getDatiPartita().getNomiGiocatori(), "; ", "."));
		TipoTerritorio tipoTesseraIniziale = ClientMain.getDatiPartita().getGiocatore(nome()).getTessere().get(0).getTipo();
		out.println("La tua tessera iniziale e' di tipo " + tipoTesseraIniziale);
	}

	public void lancioDado(Integer numero) {
		out.println("Dado lanciato: " + numero);
	}

	public void inizioTurno(String giocatore) {
		if (giocatore.equals(nome())) {
			out.println("E' il tuo turno.");
		} else {
			out.println("E' il turno di " + giocatore);
		}
	}

	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione) {
		out.println(giocatore + " ha spostato " + pecora.nomeGenerico + " " + daA(origine, destinazione));
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		String tipoRecinto = ClientMain.getDatiPartita().getRecintiFinali().length == 0 ? "iniziale" : "finale";
		out.println(giocatore + " ha spostato il pastore " + daA(origine, destinazione) + " .");
		out.println("E' stato posizionato un recinto " + tipoRecinto + " " + nelTerr(origine));
	}

	public void movimentoLupo(int origine, int destinazione) {
		out.println("Il lupo si e' spostato " + daA(origine, destinazione, "."));
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		out.println("La pecora nera si e' spostata " + daA(origine, destinazione, "."));
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		out.println("Il giocatore ha acquistato una tessera di" + tessera.getTipo().toString());
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
		out.println("Un agnello e' diventato " + adulto.nomeGenerico + nelTerr(territorio, "."));
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		out.println(pagante + " ha pagato a " + pagato + " " + denaro + " denari.");
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		out.println(giocatore + " ha posizionato il pastore sulla strada " + strada + " .");
	}

	public void ricezioneTesseraIniziale(Tessera tessera) {
		out.println("La tua tessera iniziale e' " + tessera.getTipo().toString());
	}

	public PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		List<Integer> stradeDisponibili = Utilita.indiciTrue(stradeLibere);
		out.println("Inserisci la posizione iniziale del pastore");
		out.println("Le strade disponibili sono le seguenti: ");
		out.println(listaDiInteri(stradeDisponibili, ", ", "."));

		int stradaScelta = in.leggiIntero(stradeDisponibili);

		return new PosizionamentoPastore(nome(), stradaScelta);
	}

	public MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento, int origine) {
		List<Integer> gratis = Utilita.indiciTrue(stradeLibereGratis);
		List<Integer> pagamento = Utilita.indiciTrue(stradeLibereAPagamento);
		out.println("Inserisci la posizione in cui vuoi muovere il pastore");
		out.println("Queste sono le strade gratis");
		out.println(listaDiInteri(gratis, ", ", "."));
		out.println("Queste sono le strade a pagamento");
		out.println(listaDiInteri(pagamento, " ,", "."));

		List<Integer> tutte = new ArrayList<Integer>();
		tutte.addAll(pagamento);
		tutte.addAll(gratis);

		int stradaScelta = in.leggiIntero(tutte);

		return new MovimentoPastore(nome(), origine, stradaScelta);
	}

	public SceltaMossa richiestaTipoMossa(Collection<TipoMossa> mosseDisponibili, int numMossa) {
		out.println("Devi effettuare la mossa numero" + numMossa + ".");
		out.println("Scegli la mossa da effettuare fra le seguenti:");
		out.println(menuDiScelta(mosseDisponibili));
		TipoMossa scelta = in.scegliElemento(mosseDisponibili);

		return new SceltaMossa(nome(), scelta);
	}

	public SceltaPastore richiestaPastore() {
		List<Pastore> pastori = ClientMain.getDatiPartita().getGiocatore(nome()).getPastori();
		out.println("Devi scegliere uno dei tuoi pastori");
		out.println(menuDiScelta(pastori));
		Pastore scelto = in.scegliElemento(pastori);
		
		return new SceltaPastore(nome(), scelto);
	}

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		out.println("Devi selezionare una pecora da spostare");
		int indice = selezionaPecoraDaTerritorio(t1, oviniT1, t2, oviniT2);
		List<TipoAnimale> tutti = new ArrayList<TipoAnimale>();
		tutti.addAll(oviniT1);
		tutti.addAll(oviniT2);
		TipoAnimale tipo = tutti.get(indice);
		int destinazione, origine;
		if (indice < oviniT1.size()) {
			origine = t1;
			destinazione = t2;
		} else {
			origine = t2;
			destinazione = t1;
		}
		return new MovimentoPecora(nome(), tipo, origine, destinazione);

	}

	public Abbattimento richiestaPecoraDaAbbattere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		out.println("Devi selezionare una pecora da abbattere");
		int indice = selezionaPecoraDaTerritorio(t1, oviniT1, t2, oviniT2);
		List<TipoAnimale> tutti = new ArrayList<TipoAnimale>();
		tutti.addAll(oviniT1);
		tutti.addAll(oviniT2);
		TipoAnimale tipo = tutti.get(indice);
		int terr = indice < oviniT1.size() ? t1 : t2;

		return new Abbattimento(nome(), tipo, terr);
	}

	public Accoppiamento richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili) {
		out.println("Devi selezionare un territorio in cui far accoppiare una pecora e un montone");
		out.println(menuDiScelta(territoriDisponibili));
		int terrScelto = in.scegliElemento(territoriDisponibili);
		
		return new Accoppiamento(nome(), terrScelto);
	}

	public AcquistoTessera richiestaTesseraDaAcquistare(Collection<Tessera> tessereDisp) {
		out.println("Devi selezionare una tessera da acquistare");
		out.println(menuDiScelta(tessereDisp));
		Tessera tessScelta = in.scegliElemento(tessereDisp);
		
		return new AcquistoTessera(nome(), tessScelta);
	}

	private void pulisci() {
		// per ora, nullo
		for (int i = 0; i < 0; i++) {
			out.println();
		}
	}

	private int selezionaPecoraDaTerritorio(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		if (!oviniT1.isEmpty()) {
			out.println("Queste sono le pecore " + nelTerr(t1));
		}
		out.println(menuDiScelta(oviniT1));

		if (!oviniT2.isEmpty()) {
			out.println("Queste sono le pecore " + nelTerr(t2));
		}
		out.println(menuDiScelta(oviniT2, oviniT1.size() + 1));

		return in.leggiIntero(1, oviniT1.size() + oviniT2.size()) - 1;
	}
	
	private String nome() {
		return ClientMain.getNome();
	}

}
