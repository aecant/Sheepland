package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnessioneServerSocket extends ConnessioneServer implements InterfacciaConnessioneServer {
	
	private final static Logger logger = Logger.getLogger(ConnessioneServerSocket.class.getName());
	
	private ServerSocket server;
	private final Map<Utente, GestoreClientSocket> gestoriUtenti = new Hashtable<Utente, GestoreClientSocket>();

	public ConnessioneServerSocket(ServerSheepland serverSheepland) {
		super(serverSheepland);
	}

	public void inizia() {

		try {
			server = new ServerSocket(CostantiSocket.PORTA_SERVER_SOCKET);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		logger.info("Server socket pronto");
		while (true) {
			try {
				Socket socket = server.accept();
				new GestoreClientSocket(socket, this, serverSheepland).start();
				logger.info("Connessione iniziata con " + socket);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "problemi nell'accettazione dei socket", e);
				break;
			}
		}

		try {
			server.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "problemi nella chiusura del server", e);
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {

		gestoriUtenti.get(utente).inviaEvento(evento);

	}

	public void termina() {
		for (Utente u : gestoriUtenti.keySet()) {
			gestoriUtenti.get(u).terminaConnessione();
		}
	}

	public Map<Utente, GestoreClientSocket> getGestoriUtenti() {
		return gestoriUtenti;
	}

	public void gestisciDisconnessione(Utente utente) {
		gestoriUtenti.get(utente).terminaConnessione();
		gestoriUtenti.remove(utente);
	}

	@Override
	public String toString() {
		return "Connessione Socket";
	}

}
