package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("a833c3fb-b0ab-4d2a-a670-d13cbf82be42")
public class Strada {
    @objid ("1e16149a-68e1-4a80-88bd-da78af3e8038")
    private Territorio territorio1;

    @objid ("e41b147a-e204-49a9-a3e4-3164ebfb512e")
    private Territorio territorio2;

    @objid ("2e37dbcf-e2f4-4c01-ab53-72ede9520b32")
    public boolean isConfinante(Strada altra) {
    }

    @objid ("7c05c955-6a09-4810-bc24-e298e54e5c74")
    public Territorio getTerritorio1() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.territorio1;
    }

    @objid ("f5f5c19c-100d-4363-9723-120bb8de6aa3")
    public Territorio getTerritorio2() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.territorio2;
    }

}
