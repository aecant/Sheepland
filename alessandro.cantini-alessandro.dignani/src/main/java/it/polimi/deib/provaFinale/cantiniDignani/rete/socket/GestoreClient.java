package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;

public class GestoreClient extends Thread {
	private final PrintStream LOGGER = ServerMain.LOGGER;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private GestoreCoda<Integer> codaMosse;
	private AscoltatoreSocket<Integer> ascoltatoreMosse;
	private Map<String, GestoreClient> mappaNomiSocket;

	public GestoreClient(Socket socket, GestoreCoda<Integer> codaMosse, Map<String, GestoreClient> mappaNomiSocket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.codaMosse = codaMosse;
		this.mappaNomiSocket = mappaNomiSocket;
	}

	public void run() {
		registrazioneGiocatore();

		riceviMosse();
	}

	private void riceviMosse() {
		ascoltatoreMosse = new AscoltatoreSocket<Integer>(in, codaMosse);
		ascoltatoreMosse.start();
	}

	public void inviaEvento(Evento evento) {
		try {
			out.reset();
			out.writeObject(evento);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registrazioneGiocatore() throws IOError {
		try {
			Object oggettoNome = in.readObject();
			if (!(oggettoNome instanceof String)) {
				throw new IOError(null);
			}
			String nome = (String) oggettoNome;
			if (ServerMain.aggiungiGiocatore(nome)) {
				out.writeObject(CostantiSocket.REGISTRAZIONE_OK);
				mappaNomiSocket.put(nome, this);
				LOGGER.println(nome + " registrato");
			} else {
				out.writeObject(CostantiSocket.NOME_GIA_PRESENTE);
			}
			out.flush();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void terminaConnessione() {
		ascoltatoreMosse.ferma();
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
