package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;
import java.util.List;

@objid ("b008a776-a95a-4488-9a42-816b9329278b")
public class Partita {
    @objid ("d2db4219-ff05-404f-8a7f-99ba6d492b2c")
    private int turno;

    @objid ("acd23f31-5c3c-4ffd-a60a-48180e557bc5")
    private int[] azione = new int[3];

    @objid ("aa5d42ca-7e62-4eb5-b776-8bfca5f9ed72")
    private Mazzo mazzo;

    @objid ("d24a41b1-8e8c-402d-aef0-39d7c05f2a26")
    private List<Giocatore> giocatori = new ArrayList<Giocatore> ();

    @objid ("9d312404-7277-4966-bb77-33d2bc42e4cc")
    private Mappa mappa;

    @objid ("916c6b40-9e4b-48e2-8043-dc3870903f85")
    private List<Pastore> pastori = new ArrayList<Pastore> ();

    @objid ("6301db48-0c9e-4781-9271-9c5ed989f0ab")
    private List<Recinto> recinti = new ArrayList<Recinto> ();

    @objid ("82297f8f-89ed-463f-a823-8fe5d958de53")
    private List<Agnello> agnelli = new ArrayList<Agnello> ();

    @objid ("b5c4152d-1ed2-409d-95a4-ab83c5709d21")
    private List<Pecora> pecore = new ArrayList<Pecora> ();

    @objid ("94825b36-f42e-4c8b-bb53-f956f03a4d5c")
    private PecoraNera pecoraNera;

    @objid ("616ccfac-8869-433b-9975-713ef0fd21c8")
    private Lupo lupo;

    @objid ("20637e92-38c3-42e3-902b-3359d74d563a")
    private static void turnoSuccessivo() {
    }

    @objid ("a44f746c-be40-4f64-8870-d8e8721d97e5")
    public static void abbatti(Pecora pecora) {
    }

    @objid ("90b2471a-8d02-45cf-902b-379555c7da4c")
    public static void inserisci(Recinto recinto, Strada strada) {
    }

    @objid ("b9a69884-bd6b-4761-af16-373a70cd2d0e")
    public static void inserisci(Agnello agnello, Strada strada) {
    }

    @objid ("c8f6168e-8aec-4d73-bfe6-7d2e71139317")
    public static void trasformaAgnelloInPecora(Agnello agnello) {
    }

}
