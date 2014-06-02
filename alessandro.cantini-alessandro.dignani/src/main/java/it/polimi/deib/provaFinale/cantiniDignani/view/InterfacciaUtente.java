package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoOvino;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

public interface InterfacciaUtente {

	public String chiediNome();

	public void nomeGiaPresente();

	public void inizioPartita();

	public void inizioTurno(String giocatore);

	public Mossa chiediMossa(Mossa[] mosseDisponibili);

	public void movimentoPecora(TipoOvino pecora, Territorio origine, Territorio destinazione);

	public void movimentoPastore(Pastore pastore, Strada origine, Strada destinazione);

	public void movimentoLupo(Territorio origine, Territorio destinazione);

	public void movimentoPecoraNera(Territorio origine, Territorio destinazione);

	public void lancioDado(Integer numero);

	public void acquistoTessera(String giocatore, Tessera tessera);

	public void abbattimento(String string, TipoOvino tipoOvino);

	public void trasformazioneAgnello(boolean maschio, Integer territorio);

	public void pagamento(Integer denaro, String pagante, String pagato);

	public void selezionePosizioneInizialePastore(String pastore, int strada);

	public void sceltaPosizionePastore(int[] stradeLibere);

}
