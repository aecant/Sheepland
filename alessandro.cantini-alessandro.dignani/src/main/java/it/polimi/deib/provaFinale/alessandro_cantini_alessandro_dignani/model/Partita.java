package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;
import java.util.List;

public class Partita {
    private int turno;

    private int[] azione = new int[3];

    private Mazzo mazzo;

    private List<Giocatore> giocatori = new ArrayList<Giocatore> ();

    private Mappa mappa;

    private List<Pastore> pastori = new ArrayList<Pastore> ();

    private List<Recinto> recinti = new ArrayList<Recinto> ();

    private List<Agnello> agnelli = new ArrayList<Agnello> ();

    private List<Pecora> pecore = new ArrayList<Pecora> ();

    private PecoraNera pecoraNera;

    private Lupo lupo;

    private static void turnoSuccessivo() {
    }

    public static void abbatti(Pecora pecora) {
    }

    public static void inserisci(Recinto recinto, Strada strada) {
    }

    public static void inserisci(Agnello agnello, Strada strada) {
    }

    public static void trasformaAgnelloInPecora(Agnello agnello) {
    }

}
