package controller.eventi;

import java.io.Serializable;

public interface Evento extends Serializable {

	public void aggiornaDati();

	public void visualizza();

}
