package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.util.Set;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

public interface InterfacciaUtente {

	public String chiediNome();

	public void nomeGiaPresente();

	public void inizioPartita();

	public void inizioTurno(String giocatore);

	public void movimentoPecora(TipoOvino pecora, int origine, int destinazione);

	public void movimentoPastore(Pastore pastore, int origine, int destinazione);

	public void movimentoLupo(int origine, int destinazione);

	public void movimentoPecoraNera(int origine, int destinazione);

	public void lancioDado(Integer numero);

	public void acquistoTessera(String giocatore, Tessera tessera);

	public void abbattimento(String string, TipoOvino tipoOvino, int territorio, boolean aBuonFine);

	public void trasformazioneAgnello(boolean maschio, Integer territorio);

	public void pagamento(Integer denaro, String pagante, String pagato);

	public void selezionePosizioneInizialePastore(String pastore, int strada);

	public void ricezioneTesseraIniziale(Tessera tessera);

	public void sceltaPastore(Pastore pastore);

	public PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere);

	public MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereGratis2);

	public SceltaMossa richiestaTipoMossa(Set<TipoMossa> mosseDisponibili);

	public SceltaPastore richiestaPastore();

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2);
	
	public MovimentoPecora richiestaPecoraDaAbbattere(int t1, Set<TipoOvino> oviniT1, int t2, Set<TipoOvino> oviniT2);

}
