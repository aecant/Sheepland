package tabellone;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f7cd2e87-56c9-4de5-a592-576ffc0a17df")
public class Mappa {
    @objid ("b7d02bb1-9a45-4105-a01e-aa859ace78b4")
    private List<Territorio> territorio = new ArrayList<Territorio> ();

    @objid ("c468dd34-be9c-4ce2-afb4-b5d913bd0023")
    private List<Strada> strada = new ArrayList<Strada> ();

}
