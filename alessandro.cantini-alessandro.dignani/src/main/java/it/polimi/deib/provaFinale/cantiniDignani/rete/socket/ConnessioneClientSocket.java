package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnessioneClientSocket implements InterfacciaConnessioneClient {

	private final int PORTA = CostantiRete.PORTA_SERVER;
	private final String INDIRIZZO = CostantiRete.INDIRIZZO_SERVER;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private GestoreCoda<Evento> codaEventi = ClientMain.getGestoreEventi();
	private AscoltatoreSocket<Evento> ascoltatoreEventi;

	public void inizia() {
		try {
			socket = new Socket(INDIRIZZO, PORTA);
			System.out.println("Connessione stabilita: " + socket); // TODO da
																	// rimuovere

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registraGiocatore(String nome) throws NomeGiaPresenteException {
		try {
			out.reset();
			out.writeObject(nome);
			out.flush();
			Character rispostaServer = (Character) in.readObject();
			if (rispostaServer == CostantiSocket.NOME_GIA_PRESENTE) {
				throw new NomeGiaPresenteException();
			}
			if (rispostaServer != CostantiSocket.REGISTRAZIONE_OK) {
				throw new IOError(null);
			}
			ascoltatoreEventi = new AscoltatoreSocket<Evento>(in, codaEventi);
			ascoltatoreEventi.start();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inviaMossa(Integer mossaScelta) {
		try {
			out.reset();
			out.writeObject(mossaScelta);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatiPartita scaricaDatiPartita() {
		// TODO Auto-generated method stub
		return null;
	}

	public void termina() {
		ascoltatoreEventi.ferma();
		try {
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
