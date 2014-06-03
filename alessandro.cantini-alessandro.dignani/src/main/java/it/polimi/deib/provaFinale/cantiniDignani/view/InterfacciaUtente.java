package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.util.Set;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoOvino;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

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

	public void abbattimento(String string, TipoOvino tipoOvino);

	public void trasformazioneAgnello(boolean maschio, Integer territorio);

	public void pagamento(Integer denaro, String pagante, String pagato);

	public void selezionePosizioneInizialePastore(String pastore, int strada);

	public Mossa richiestaPosizionePastore(int[] stradeLibere);

	public void ricezioneTesseraIniziale(Tessera tessera);

	public Mossa richiestaTipoMossa(Set<TipoMossa> mosseDisponibili);

	public void sceltaPastore(Pastore pastore);

	public Mossa richiestaPastore();

}
