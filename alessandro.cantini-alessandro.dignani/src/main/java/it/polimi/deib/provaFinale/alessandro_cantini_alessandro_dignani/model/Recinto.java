package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("bc7593fb-14f8-4339-ab7d-a7940841df0b")
public class Recinto extends PedinaSuStrada {
    @objid ("87f15e6a-ace6-49a2-971d-6dcbc1bf82b3")
    private boolean finale;

    @objid ("39317d74-2397-432f-bf62-be0b9c84e26c")
    boolean isFinale() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.finale;
    }

}
