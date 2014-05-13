package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8099813c-f115-4821-b85d-96f1ab1e2ebf")
public class Mazzo {
    @objid ("69a56825-932a-4e83-84b6-26a5160d9f88")
    private List<Tessera> tessere = new ArrayList<Tessera> ();

    @objid ("3d41cf42-5356-4c6a-b68a-f3be792b1b6c")
    public Tessera prelevaCarta(TipoTerritorio tipo) {
    }

    @objid ("02d31fd0-7474-4674-b710-f8d9f26a40eb")
    public void distribuisciTessereIniziali() {
    }

}
