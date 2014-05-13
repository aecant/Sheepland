package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import java.util.ArrayList;
import java.util.List;

public class Mappa {
    private List<Territorio> territorio = new ArrayList<Territorio> ();

    private List<Strada> strada = new ArrayList<Strada> ();

    public static void creaMappa() {
    }

    Strada getStrada() {
        return this.strada;
    }

    void setStrada(Strada value) {
        this.strada = value;
    }

}
