package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.CostantiRete;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaConnessioneServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketImpl implements InterfacciaConnessioneServer {
	private final int PORTA = CostantiRete.PORTA_SERVER;
	private final PrintStream LOGGER = ServerMain.LOGGER;

	private ServerSocket server;
	private Socket socket;
	private ExecutorService esecutore;
	private Map<String, GestoreClient> mappaNomiSocket = new Hashtable<String, GestoreClient>();

	private GestoreCoda<Integer> codaMosse;

	public ServerSocketImpl(GestoreCoda<Integer> gestoreCoda) {
		this.codaMosse = gestoreCoda;
	}

	public void inizia() {
		esecutore = Executors.newCachedThreadPool();

		try {
			server = new ServerSocket(PORTA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		LOGGER.println("Server pronto");
		while (true) {
			try {
				socket = server.accept();
				esecutore.submit(new GestoreClient(socket, codaMosse, mappaNomiSocket));
				// new GestoreClient(socket, codaMosse,
				// mappaNomiSocket).start();
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

	public void inviaEvento(Evento evento, List<String> giocatori) {
	
		for (String giocatore : giocatori) {
			try {
				mappaNomiSocket.get(giocatore).inviaEvento(evento);
				LOGGER.println(evento + " inviato a " + giocatore);
			} catch (NullPointerException e) {
				throw new NullPointerException(giocatore + " non presente in " + giocatori);
			}
		}

	}

	public void termina() {
		esecutore.shutdown();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
