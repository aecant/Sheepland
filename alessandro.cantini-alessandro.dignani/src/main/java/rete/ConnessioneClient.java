package rete;

import model.Giocatore;
import model.Pastore;
import model.Tessera;
import controller.eventi.Evento;
import controller.eventi.Mossa;

public interface ConnessioneClient {

	public DatiTerritorio[] chiediElencoTerritori();

	public Integer[] chiediStradeConfinanti(Integer strada);

	public Integer[] chiediStradeLibere();

	public DatiTerritorio[] chediPecoreTerritoriVicini(Integer strada);

	public Pastore[] chiediPastori();

	public Integer[] chiediRecintiIniziali();

	public Integer[] chiediRecintiFinali();

	public String chiediGiocatoreDiTurno();

	public Tessera[] chiediTessereTerritoriConfinanti(Integer strada);

	public Evento chiediEvento();

	public Mossa[] chiediMosseDisponibili();

	public Tessera[] chiediTessere();

	public Tessera[] chiediTessereInCima();

	public void inviaMossa(Mossa mossaScelta);

	public Giocatore[] chiediGiocatori();
}
