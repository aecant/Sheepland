package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ec4a6fa4-a6de-4171-93d1-aa79d6755d13")
public class Giocatore {
    @objid ("fa516b6c-0445-4e10-a09f-101a0eadc56b")
    private String nome;

    @objid ("4169fb82-eb22-4bac-969b-1be6207b7360")
    private int denaro;

    @objid ("e9d62b54-c2af-4c54-badb-c3d527c7d5cb")
    private List<Tessera> tessere = new ArrayList<Tessera> ();

    @objid ("2ea83a29-2ce7-4eef-a1f3-846bb3166a09")
    private List<Pastore> pastore = new ArrayList<Pastore> ();

    @objid ("3180cc6d-b95b-4992-ab53-81cc7e6adf04")
    public String getNome() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nome;
    }

    @objid ("64c8946c-4af5-473f-9962-50d8534434b6")
    public int getDenaro() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.denaro;
    }

    @objid ("5587ac21-c4a2-4cd5-8157-3ce947df5232")
    public void aggiungiDenaro(int quantita) {
    }

    @objid ("c0bed27c-f0bf-4366-a675-64ef2fc6a27f")
    public void sottraiDenaro() {
    }

    @objid ("8589c31a-90eb-4dce-a032-6bdab6926901")
    public Pastore getPastore() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.pastore;
    }

    @objid ("51e65399-909f-4ed8-b288-3eddbf9fa145")
    public void mettiInVendita(Tessera tessera) {
    }

    @objid ("4ce5c74f-314b-49e5-995a-aaf3d0df9d8c")
    public void compra(Tessera tessera) {
    }

    @objid ("271d3ab1-e670-4a93-bcb9-85a3b32279b8")
    public void prendiTessera(TipoTerritorio tipo) {
    }

}
