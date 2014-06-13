package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Abbattimento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Accoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.AcquistoTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecora;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Collection;

public interface InterfacciaUtente {

	String chiediNome();

	void nomeGiaPresente();

	void inizioPartita();

	void inizioTurno(String giocatore);

	void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione);

	void movimentoPastore(String giocatore, int origine, int destinazione);

	void movimentoLupo(int origine, int destinazione);

	void movimentoPecoraNera(int origine, int destinazione);

	void lancioDado(Integer numero);

	void acquistoTessera(String giocatore, Tessera tessera);

	void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine);

	void accoppiamento(String giocatore, int territorio, boolean aBuonFine);

	void trasformazioneAgnello(boolean maschio, Integer territorio);

	void pagamento(Integer denaro, String pagante, String pagato);

	void selezionePosizioneInizialePastore(String giocatore, int strada);

	void ricezioneTesseraIniziale(Tessera tessera);

	PosizionamentoPastore richiestaPosizioneInizialePastore(boolean[] stradeLibere);

	MovimentoPastore richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento, int origine);

	SceltaMossa richiestaTipoMossa(Collection<TipoMossa> mosseDisponibili, int numMossa);

	SceltaPastore richiestaPastore();

	MovimentoPecora richiestaPecoraDaMuovere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2);

	Abbattimento richiestaPecoraDaAbbattere(int t1, Collection<TipoAnimale> oviniT1, int t2, Collection<TipoAnimale> oviniT2);

	Accoppiamento richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili);

	AcquistoTessera richiestaTesseraDaAcquistare(Collection<Tessera> tessereDisponibili);

}
