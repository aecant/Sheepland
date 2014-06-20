package it.polimi.deib.provaFinale.cantiniDignani.model;

public class Agnello extends Pecora {

	private int eta;

	/**
	 * Instanzia un agnello a partire dalla strada e dal sesso
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

	/**
	 * Restituisce l'eta' dell'agnello
	 * @return l'eta' dell'agnello
	 */
	public int getEta() {
		return eta;
	}
	
	/**
	 * Restituisce il tipo di Animale dell'agnello
	 */
	@Override
	public TipoAnimale getTipoAnimale() {
		return TipoAnimale.AGNELLO;
	}
	
	/**
	 * Restituisce l'hash code dell'agnello
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + eta;
		return result;
	}
	
	/**
	 * Compara l'agnello con un altro oggetto usando la posizione, il sesso e l'eta'
	 */
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

	/**
	 * Restituisce una rappresentazione dell'agnello sotto forma di stringa
	 */
	@Override
	public String toString() {
		return "Agnello, eta' " + eta + " in territorio " + super.getPosizione().getCodice();
	}

}
