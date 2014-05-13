package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private String nome;

    private int denaro;

    private List<Tessera> tessere = new ArrayList<Tessera> ();

    private List<Pastore> pastore = new ArrayList<Pastore> ();

    public String getNome() {
        return this.nome;
    }

    public int getDenaro() {
        return this.denaro;
    }

    public void aggiungiDenaro(int quantita) {
    }

    public void sottraiDenaro() {
    }

    public void mettiInVendita(Tessera tessera) {
    }

    public void compra(Tessera tessera) {
    }

    public void prendiTessera(TipoTerritorio tipo) {
    }

}
