package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class Strada {
	private Territorio territorio1;
	private Territorio territorio2;

	public Strada(Territorio territorio1, Territorio territorio2) throws IllegalArgumentException{
		
		try {
			if (territorio1.equals(territorio2))
				throw new IllegalArgumentException("I due territori devono essere diversi");
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("I territori non devono essere null");
		}
		this.territorio1 = territorio1;
		this.territorio2 = territorio2;
	}

	public Territorio getTerritorio1() {
		return this.territorio1;
	}

	public Territorio getTerritorio2() {
		return this.territorio2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Strada))
			return false;
		Strada other = (Strada) obj;
		if((territorio1.equals(other.territorio1) && territorio2.equals(other.territorio2)) || 
				territorio1.equals(other.territorio2) && territorio2.equals(other.territorio1))
			return true;
		
		return false;
	}

}