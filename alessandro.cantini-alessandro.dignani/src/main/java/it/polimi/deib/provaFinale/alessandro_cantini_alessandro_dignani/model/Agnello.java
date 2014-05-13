package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;


public class Agnello extends Pecora {
    private int eta;

    public void invecchia() {
    	eta++;
    	if(eta > Costanti.ETA_MASSIMA_AGNELLO)
    		Partita.trasformaAgnelloInPecora(this);
    }
  
}
