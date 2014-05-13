package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("01f8ca65-6472-49c5-9d02-b61255abaac1")
public class Pecora extends Animale {
    @objid ("3c9a8496-6c6b-4060-8a69-57804ceee819")
    private boolean maschio;

    @objid ("71d532ba-78a5-4763-a586-c4b1be78d526")
    public boolean isMaschio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.maschio;
    }

    @objid ("bb708c8c-f415-4537-b95e-07a3063a7149")
    public Territorio scegliMovimento() {
    }

}
