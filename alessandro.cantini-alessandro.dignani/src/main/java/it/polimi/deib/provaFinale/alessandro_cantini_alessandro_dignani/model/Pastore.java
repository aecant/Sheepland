package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7c2c6267-8470-49bc-9d17-00a37833d124")
public class Pastore extends PedinaSuStrada {
    @objid ("d3100061-310d-451b-92d7-4c79e215a800")
    private Colore colore;

    @objid ("6368a8bd-e535-4fc7-a863-be810b070357")
    public void muoviIn(Strada posizione) {
    }

    @objid ("4e00759b-f7a5-4ecc-b491-fac940ab1c7a")
    public Strada scegliMovimento() {
    }

    @objid ("9e586805-d88f-4f8d-8665-39f209abf1e7")
    public Colore getColore() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.colore;
    }

    @objid ("2b3fd555-5a8d-4bb1-b3ea-1c170834c947")
    public void setColore(Colore value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.colore = value;
    }

    @objid ("a8e8a386-9542-4a47-86d7-9111d1814039")
    public enum Colore {
        ;
    }

}
