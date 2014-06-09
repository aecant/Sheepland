package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.util.Collection;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

public interface InterfacciaUtente {

	public String chiediNome();

	public void nomeGiaPresente();

	public void inizioPartita();

	public void inizioTurno(String giocatore);
	
	public void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione);

	public void movimentoPastore(String giocatore, int origine, int destinazione);

	public void movimentoLupo(int origine, int destinazione);

	public void movimentoPecoraNera(int origine, int destinazione);

	public void lancioDado(Integer numero);

	public void acquistoTessera(String giocatore, Tessera tessera);

	public void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine);

	public void accoppiamento(String giocatore, int territorio, boolean aBuonFine);

	public void trasformazioneAgnello(boolean maschio, Integer territorio);

	public void pagamento(Integer denaro, String pagante, String pagato);

	public void selezionePosizioneInizialePastore(String giocatore, int strada);

	public void ricezioneTesseraIniziale(Tessera tessera);

	public void sceltaPastore(String giocatore, Pastore pastore);

	public PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere);

	public MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereGratis2);

	public SceltaMossa richiestaTipoMossa(Collection<TipoMossa> mosseDisponibili);

	public SceltaPastore richiestaPastore();

	public MovimentoPecora richiestaPecoraDaMuovere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2);
	
	public MovimentoPecora richiestaPecoraDaAbbattere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2);

	public Accoppiamento richiestaTerritorioPerAccoppiamento(Collection<Integer> terrDisponibili);

	public AcquistoTessera richiestaTesseraDaAcquistare(Collection<Tessera> tessereDisp);

}
