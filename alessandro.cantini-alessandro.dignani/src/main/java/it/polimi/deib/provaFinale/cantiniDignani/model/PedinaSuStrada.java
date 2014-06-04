package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.io.Serializable;

public class PedinaSuStrada implements Serializable{

	private static final long serialVersionUID = -6921466505175740285L;
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
