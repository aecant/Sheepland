package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static it.polimi.deib.provaFinale.cantiniDignani.view.cli.UtilitaStringhe.*;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
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
import it.polimi.deib.provaFinale.cantiniDignani.view.InterfacciaUtente;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Cli implements InterfacciaUtente {
	private final InputCli in = new InputCli(CostantiCli.DEFAULT_INPUT);
	private final PrintStream out = CostantiCli.DEFAULT_OUTPUT;

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
	}

	public void lancioDado(Integer numero) {
		out.println("Dado lanciato: " + numero);
	}

	public void inizioTurno(String giocatore) {
		if (giocatore.equals(ClientMain.getNome())) {
			out.println("E' il tuo turno.");
		} else {
			out.println("E' il turno di " + giocatore);
		}
	}

	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione) {
		out.println(giocatore + " ha spostato " + pecora.nomeGenerico + daA(origine, destinazione));
	}

	public void movimentoPastore(String giocatore, int origine, int destinazione) {
		out.println(giocatore + " ha spostato il pastore " + daA(origine, destinazione) + " .");
	}

	public void movimentoLupo(int origine, int destinazione) {
		out.println("Il lupo si e' spostato " + daA(origine, destinazione, true));
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		out.println("La pecora si e' spostata " + daA(origine, destinazione, true));
	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		out.println("Il giocatore ha acquistato una tessera di" + inizialeMaiuscola(tessera.getTipo().toString()));
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
		// TODO Auto-generated method stub

	}

	public void sceltaPastore(Pastore pastore) {
		// TODO Auto-generated method stub

	}

	public PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereGratis2) {
		// TODO Auto-generated method stub
		return null;
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
