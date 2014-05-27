package view;

import model.Pastore;
import model.Strada;
import model.Territorio;
import model.Tessera;
import controller.TipoOvino;
import controller.eventi.Mossa;

public interface InterfacciaUtente {

	public String chiediNome();

	public void nomeGiaPresente();

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

	public void inizioPartita();

}
