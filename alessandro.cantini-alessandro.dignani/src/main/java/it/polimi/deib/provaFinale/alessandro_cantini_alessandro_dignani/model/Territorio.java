package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("86220001-a086-47fe-87dd-54390d526387")
public class Territorio {
    @objid ("934aa81a-899a-424f-9e2e-6fcafa9ade23")
    private TipoTerritorio tipo;

    @objid ("8a40df6d-d2fa-4d75-9454-fe9eb68849f3")
    private int codice;

    @objid ("544d50ee-ad9a-43c6-9926-88f3530f8ba4")
    public TipoTerritorio getTipo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tipo;
    }

    @objid ("54fed8d7-f471-45ff-8ee7-259471c10ca2")
    public Territorio territorioConfinante(short numeroDado) {
    }

    @objid ("52ed5a0f-638a-426a-92fa-f34b6dd2c094")
    public int getCodice() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.codice;
    }

}
