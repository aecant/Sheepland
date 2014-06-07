package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

import java.io.PrintStream;
import java.util.Set;

public class Cli implements InterfacciaUtente {
	private PrintStream out = Costanti.OUTPUT;

	public String chiediNome() {
		pulisci();
		out.println("Inserisci il tuo nome");
		return Input.readString();
	}

	public void nomeGiaPresente() {
		out.println("Questo nome e' gia' registrato");
	}

	public void inizioPartita() {
		out.println("Partita iniziata!");
	}

	public void lancioDado(Integer numero) {
		out.println("Dado lanciato: " + numero);
	}

	private void pulisci() {
		// per ora, nullo
		for (int i = 0; i < 0; i++) {
			out.println();
		}
	}

	public void inizioTurno(String giocatore) {
		// TODO Auto-generated method stub

	}

	public void movimentoPecora(TipoOvino pecora, int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoPastore(Pastore pastore, int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoLupo(int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		// TODO Auto-generated method stub

	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		// TODO Auto-generated method stub

	}

	public void abbattimento(String string, TipoOvino tipoOvino, int territorio, boolean aBuonFine) {
		// TODO Auto-generated method stub

	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		// TODO Auto-generated method stub

	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		// TODO Auto-generated method stub

	}

	public void selezionePosizioneInizialePastore(String pastore, int strada) {
		// TODO Auto-generated method stub

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

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

	public MovimentoPecora richiestaPecoraDaAbbattere(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2) {
		// TODO Auto-generated method stub
		return null;
	}

}
