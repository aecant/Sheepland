package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.ConnessioneServer;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnessioneServerSocket extends ConnessioneServer implements InterfacciaConnessioneServer {

	public ConnessioneServerSocket(ServerSheepland serverSheepland) {
		super(serverSheepland);
	}

	private final int PORTA = CostantiRete.PORTA_SERVER_SOCKET;
	private final PrintStream LOGGER = ServerSheepland.LOGGER;

	private ServerSocket server;
	private final ExecutorService esecutore = Executors.newCachedThreadPool();
	private final Map<Utente, GestoreClientSocket> gestoriUtenti = new Hashtable<Utente, GestoreClientSocket>();

	public void inizia() {

		try {
			server = new ServerSocket(PORTA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		LOGGER.println("Server socket pronto");
		while (true) {
			try {
				Socket socket = server.accept();
				esecutore.submit(new GestoreClientSocket(socket, this, serverSheepland));
				LOGGER.println("Connessione iniziata con " + socket);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inviaEvento(Evento evento, Utente utente) {

		gestoriUtenti.get(utente).inviaEvento(evento);

	}

	public void termina() {
		for (Utente u : gestoriUtenti.keySet()) {
			gestoriUtenti.get(u).terminaConnessione();
		}
		esecutore.shutdown();
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
