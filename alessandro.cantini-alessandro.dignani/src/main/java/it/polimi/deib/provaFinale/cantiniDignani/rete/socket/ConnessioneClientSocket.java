package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnessioneClientSocket implements InterfacciaConnessioneClient {

	private final static Logger logger = Logger.getLogger(ConnessioneClientSocket.class.getName());

	private final int PORTA = CostantiRete.PORTA_SERVER_SOCKET;
	private final String INDIRIZZO = CostantiRete.INDIRIZZO_SERVER;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private GestoreCoda<Evento> codaEventi = MainClient.getGestoreEventi();
	private AscoltatoreSocket<Evento> ascoltatoreEventi;

	public void inizia() {
		try {
			socket = new Socket(INDIRIZZO, PORTA);
			logger.info("Connessione stabilita: " + socket);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registraGiocatore(Coppia<String, String> nomeEPassword) throws NomeGiaPresenteException, PasswordSbagliataException {
		try {
			out.reset();
			out.writeObject(nomeEPassword);
			out.flush();
			Character rispostaServer = (Character) in.readObject();

			switch (rispostaServer) {
			case CostantiSocket.NOME_GIA_PRESENTE:
				throw new NomeGiaPresenteException();
			case CostantiSocket.PASSWORD_SBAGLIATA:
				throw new PasswordSbagliataException();
			case CostantiSocket.REGISTRAZIONE_OK:
				break;
			default:
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
