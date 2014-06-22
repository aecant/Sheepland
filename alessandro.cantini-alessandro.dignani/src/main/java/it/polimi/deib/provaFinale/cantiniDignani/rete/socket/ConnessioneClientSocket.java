package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneClient;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.view.cli.CostantiCli;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneClientSocket extends ConnessioneClient implements InterfacciaConnessioneClient {

	private final static Logger LOGGER = Logger.getLogger(ConnessioneClientSocket.class.getName());

	private final int PORTA = CostantiSocket.PORTA_SERVER_SOCKET;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private AscoltatoreSocket<Evento> ascoltatoreEventi;

	public ConnessioneClientSocket(String indirizzoServer, GestoreCoda<Evento> codaEventi) {
		super(indirizzoServer, codaEventi);
	}

	public void inizia() {
		try {
			socket = new Socket(getIndirizzoServer(), PORTA);
			LOGGER.info("Connessione stabilita: " + socket);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Errore nella comunicazione con il server", e);
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

			ascoltatoreEventi = new AscoltatoreSocket<Evento>(in, getCodaEventi());
			ascoltatoreEventi.start();

		} catch (IOException e) {
			CostantiCli.DEFAULT_OUTPUT.println("Connessione con il server interrotta");
			LOGGER.log(Level.SEVERE, "comunicazione con il server interrotta", e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "problemi nella ricezione dal server", e);
		}

	}

	public void inviaMossa(Integer mossaScelta) {
		try {
			out.reset();
			out.writeObject(mossaScelta);
			out.flush();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Errore nell'invio della mossa", e);

		}
	}

	public void termina() {
		ascoltatoreEventi.ferma();
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Errore nella chiusura della connessione", e);

		}
	}

}
