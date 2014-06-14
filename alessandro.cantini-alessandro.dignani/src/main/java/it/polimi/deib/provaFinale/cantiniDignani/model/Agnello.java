package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Agnello extends Pecora {

	private int eta;

	/**
	 * Instanzia un agnello
	 * 
	 * @param posizione
	 *            il territorio su cui si trova
	 * @param maschio
	 *            true se e' un montone, false se e' una pecora
	 */
	public Agnello(Territorio posizione, boolean maschio) {
		super(posizione, maschio);
		eta = 0;
	}

	/**
	 * Aumenta di uno l'eta' dell'agnello
	 */
	public void invecchia() {
		eta++;
	}

	public int getEta() {
		return eta;
	}

	@Override
	public TipoAnimale getTipoAnimale() {
		return TipoAnimale.AGNELLO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + eta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Agnello)) {
			return false;
		}
		Agnello other = (Agnello) obj;
		if (eta != other.eta) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Agnello, eta' "+eta+" in territorio "+ super.getPosizione().getCodice();
	}
	
	

}
