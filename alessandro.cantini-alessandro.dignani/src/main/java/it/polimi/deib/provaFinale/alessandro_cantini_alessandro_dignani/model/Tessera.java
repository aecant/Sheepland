package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ec67347a-1e21-4235-9872-eb6bf4bf1d7e")
public class Tessera {
    @objid ("128a416c-764f-47f6-a41a-a7e30299cc65")
    private int costo;

    @objid ("075e4576-952b-4a21-a592-887e62c8f3d7")
    private TipoTerritorio tipo;

    @objid ("6dc3f396-e7bf-45e3-928d-0911668576b0")
    public int getCosto() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.costo;
    }

    @objid ("4d56d60c-b115-4ab9-ad5a-570756e5c530")
    public TipoTerritorio getTipo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tipo;
    }

}
