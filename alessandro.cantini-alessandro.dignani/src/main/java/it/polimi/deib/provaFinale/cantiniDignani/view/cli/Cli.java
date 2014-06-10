package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.*;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Accoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.AcquistoTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecora;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Cli implements InterfacciaUtente {
	private final InputCli in = new InputCli(CostantiCli.DEFAULT_INPUT);
	private final PrintStream out = CostantiCli.DEFAULT_OUTPUT;
	private final String nome = ClientMain.getNome();
	
	
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
		out.println(listaDiStringhe(ClientMain.getDatiPartita().getNomiGiocatori(), " ;", "."));
		TipoTerritorio tipoTesseraIniziale = ClientMain.getDatiPartita().getGiocatore(nome).getTessere().get(0).getTipo();
		out.println("La tua tessera iniziale e' di tipo " + tipoTesseraIniziale);	
	}

	public void lancioDado(Integer numero) {
		out.println("Dado lanciato: " + numero);
	}

	public void inizioTurno(String giocatore) {
		if (giocatore.equals(nome)) {
			out.println("E' il tuo turno.");
		} else {
			out.println("E' il turno di " + giocatore);
		}
	}

	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione) {
		out.println(giocatore + " ha spostato " + pecora.nomeGenerico + daA(origine, destinazione));
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		String tipoRecinto = ClientMain.getDatiPartita().getRecintiFinali().length == 0 ? "iniziale" : "finale";
		out.println(giocatore + " ha spostato il pastore " + daA(origine, destinazione) + " .");
		out.println("E' stato posizionato un recinto " + tipoRecinto + " " + nelTerr(origine));
	}

	public void movimentoLupo(int origine, int destinazione) {
		out.println("Il lupo si e' spostato " + daA(origine, destinazione, true));
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		out.println("La pecora si e' spostata " + daA(origine, destinazione, true));
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		out.println("Il giocatore ha acquistato una tessera di" + tessera.getTipo().toString());
	}

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? "ha abbattuto " : "non e' riuscito ad abbattere ";
		out.println(giocatore + verbo + tipo.nomeGenerico + nelTerr(territorio, true));
	}

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine) {
		String verbo = aBuonFine ? "ha fatto" : "non e' riuscito a far";
		out.println("Il giocatore " + verbo + " una pecora e un montone nel territorio " + nelTerr(territorio, true));
	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		TipoAnimale adulto = maschio ? TipoAnimale.MONTONE : TipoAnimale.PECORA;
		out.println("Un agnello e' diventato " + adulto.nomeGenerico + nelTerr(territorio, true));
	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		out.println(pagante + " ha pagato a " + pagato + denaro + " denari.");
	}

	public void selezionePosizioneInizialePastore(String giocatore, int strada) {
		out.println(giocatore + "ha posizionato il pastore sulla strada " + strada + " .");
	}

	public void ricezioneTesseraIniziale(Tessera tessera) {
		out.println("La tua tessera iniziale e' " + tessera.getTipo().toString());
	}

	public PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		List<Integer> stradeDisponibili = Utilita.indiciTrue(stradeLibere);
		out.println("Inserisci la posizione iniziale del pastore");
		out.println("Le strade disponibili sono le seguenti: ");
		out.println(listaDiInteri(stradeDisponibili, " ,", "."));
		
		int stradaScelta = in.leggiIntero(stradeDisponibili);
		
		return new PosizionamentoPastore(nome, stradaScelta);		
	}

	public MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento, int origine) {
		List<Integer> gratis = Utilita.indiciTrue(stradeLibereGratis);
		List<Integer> pagamento = Utilita.indiciTrue(stradeLibereAPagamento);
		out.println("Inserisci la posizione in cui vuoi muovere il pastore");
		out.println("Queste sono le strade gratis");
		out.println(listaDiInteri(gratis, " ,", "."));
		out.println("Queste sono le strade a pagamento");
		out.println(listaDiInteri(pagamento, " ,", "."));
		
		List<Integer> tutte = new ArrayList<Integer>() ;
		tutte.addAll(pagamento);
		tutte.addAll(gratis);
		
		int stradaScelta = in.leggiIntero(tutte);
		
		return new MovimentoPastore(nome, origine, stradaScelta);
	}

	public SceltaMossa richiestaTipoMossa(Set<TipoMossa> mosseDisponibili) {
		// TODO Auto-generated method stub
		return null;
	}

	public SceltaPastore richiestaPastore() {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Set<TipoAnimale> oviniT1, int t2, Set<TipoAnimale> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPecora richiestaPecoraDaAbbattere(int t1, Set<TipoAnimale> oviniT1, int t2, Set<TipoAnimale> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Accoppiamento richiestaTerritorioPerAccoppiamento(List<Integer> terrDisponibili) {
		// TODO Auto-generated method stub
		return null;
	}

	public SceltaMossa richiestaTipoMossa(Collection<TipoMossa> mosseDisponibili) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPecora richiestaPecoraDaAbbattere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Accoppiamento richiestaTerritorioPerAccoppiamento(Collection<Integer> terrDisponibili) {
		// TODO Auto-generated method stub
		return null;
	}

	public AcquistoTessera richiestaTesseraDaAcquistare(Collection<Tessera> tessereDisp) {
		// TODO Auto-generated method stub
		return null;
	}

	private void pulisci() {
		// per ora, nullo
		for (int i = 0; i < 0; i++) {
			out.println();
		}
	}

}
