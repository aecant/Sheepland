package gioco;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ec4a6fa4-a6de-4171-93d1-aa79d6755d13")
public class Giocatore {
    @objid ("fa516b6c-0445-4e10-a09f-101a0eadc56b")
    private String nome;

    @objid ("ce658f31-d00c-4ade-ae57-54e09c6388ae")
    private Denaro denaro;

    @objid ("3180cc6d-b95b-4992-ab53-81cc7e6adf04")
    String getNome() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nome;
    }

    @objid ("b6a52d4d-22f1-4e6d-9af5-6be1675c3a22")
    void setNome(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nome = value;
    }

    @objid ("7bb45018-8081-4e81-843c-908b61752e34")
    Denaro getDenaro() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.denaro;
    }

    @objid ("36842b93-3052-4ee7-af18-3d720c175af2")
    void setDenaro(Denaro value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.denaro = value;
    }

}
