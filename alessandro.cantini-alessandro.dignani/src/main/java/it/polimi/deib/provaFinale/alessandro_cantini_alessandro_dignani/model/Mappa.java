package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f7cd2e87-56c9-4de5-a592-576ffc0a17df")
public class Mappa {
    @objid ("b7d02bb1-9a45-4105-a01e-aa859ace78b4")
    private List<Territorio> territorio = new ArrayList<Territorio> ();

    @objid ("13c88fba-1820-4a84-9364-ee0178f9b448")
    private List<Strada> strada = new ArrayList<Strada> ();

    @objid ("3d6789c9-66fb-441b-a793-9c5746433926")
    public static void creaMappa() {
    }

    @objid ("9cded406-9a00-4576-9d4b-0abcc98dbfa7")
    Strada getStrada() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.strada;
    }

    @objid ("4ec258d3-65fc-4db9-8dc1-2ec096bb0d14")
    void setStrada(Strada value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.strada = value;
    }

}
