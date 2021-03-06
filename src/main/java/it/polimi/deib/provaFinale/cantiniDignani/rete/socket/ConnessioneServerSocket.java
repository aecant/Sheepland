package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneServerSocket extends ConnessioneServer implements InterfacciaConnessioneServer {

	private static final Logger LOGGER = Logger.getLogger(ConnessioneServerSocket.class.getName());

	private final Map<Utente, GestoreClientSocket> gestoriUtenti = new HashMap<Utente, GestoreClientSocket>();

	public ConnessioneServerSocket(ServerSheepland serverSheepland) {
		super(serverSheepland);
	}

	public void inizia() {
		ServerSocket server;
		try {
			server = new ServerSocket(CostantiSocket.PORTA_SERVER_SOCKET);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Errore nel creare il server socket", e);
			return;
		}
		LOGGER.info("Server socket pronto");
		while (true) {
			try {
				Socket socket = server.accept();
				new GestoreClientSocket(socket, this, serverSheepland).start();
				LOGGER.info("Connessione iniziata con " + socket);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "problemi nell'accettazione dei socket", e);
				break;
			}
		}

		try {
			server.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "problemi nella chiusura del server", e);
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {

		getGestoriUtenti().get(utente).inviaEvento(evento);

	}

	public void termina() {
		for (Utente u : getGestoriUtenti().keySet()) {
			getGestoriUtenti().get(u).terminaConnessione();
		}
	}

	public synchronized Map<Utente, GestoreClientSocket> getGestoriUtenti() {
		return gestoriUtenti;
	}

	public void gestisciDisconnessione(Utente utente) {
		getGestoriUtenti().get(utente).terminaConnessione();
		getGestoriUtenti().remove(utente);
	}

	@Override
	public String toString() {
		return "Connessione Socket";
	}

}
