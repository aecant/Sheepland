package rete;

public class DatiTerritorio {
	private int numPecore, numMontoni, numAgnelli;
	private boolean lupo, pecoraNera;
	
	public DatiTerritorio(int numPecore, int numMontoni, int numAgnelli, boolean lupo, boolean pecoraNera) {
		this.numPecore = numPecore;
		this.numMontoni = numMontoni;
		this.numAgnelli = numAgnelli;
		this.lupo = lupo;
		this.pecoraNera = pecoraNera;
	}

	public int getNumPecore() {
		return numPecore;
	}

	public int getNumMontoni() {
		return numMontoni;
	}

	public int getNumAgnelli() {
		return numAgnelli;
	}

	public boolean isLupo() {
		return lupo;
	}

	public boolean isPecoraNera() {
		return pecoraNera;
	}

}
