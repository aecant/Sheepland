package view;

import java.io.PrintStream;

import model.Pastore;
import model.Strada;
import model.Territorio;
import model.Tessera;
import controller.TipoOvino;
import controller.eventi.Mossa;

public class Cli implements InterfacciaUtente {
	private PrintStream out = Costanti.OUTPUT;
	
	
	public String chiediNome() {
		
		return null;
	}

	public void nomeGiaPresente() {
		// TODO Auto-generated method stub

	}

	public void inizioTurno(String giocatore) {
		// TODO Auto-generated method stub

	}

	public Mossa chiediMossa(Mossa[] mosseDisponibili) {
		// TODO Auto-generated method stub
		return null;
	}

	public void movimentoPecora(TipoOvino pecora, Territorio origine, Territorio destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoPastore(Pastore pastore, Strada origine, Strada destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoLupo(Territorio origine, Territorio destinazione) {
		// TODO Auto-generated method stub

	}

	public void movimentoPecoraNera(Territorio origine, Territorio destinazione) {
		// TODO Auto-generated method stub

	}

	public void lancioDado(Integer numero) {
		// TODO Auto-generated method stub

	}

	public void acquistoTessera(String giocatore, Tessera tessera) {
		// TODO Auto-generated method stub

	}

	public void abbattimento(String string, TipoOvino tipoOvino) {
		// TODO Auto-generated method stub

	}

	public void trasformazioneAgnello(boolean maschio, Integer territorio) {
		// TODO Auto-generated method stub

	}

	public void pagamento(Integer denaro, String pagante, String pagato) {
		// TODO Auto-generated method stub

	}

	public void inizioPartita() {
		// TODO Auto-generated method stub

	}

}
