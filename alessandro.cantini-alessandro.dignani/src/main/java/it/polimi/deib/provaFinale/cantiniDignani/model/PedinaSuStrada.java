package it.polimi.deib.provaFinale.cantiniDignani.model;

public class PedinaSuStrada {
	private Strada strada;

	public PedinaSuStrada(Strada strada) {
		this.strada = strada;
	}

	public Strada getStrada() {
		return this.strada;
	}

	protected void setStrada(Strada strada) {
		this.strada = strada;
	}
}
