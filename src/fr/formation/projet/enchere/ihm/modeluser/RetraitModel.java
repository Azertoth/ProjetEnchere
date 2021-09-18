package fr.formation.projet.enchere.ihm.modeluser;

import fr.formation.projet.enchere.bo.Retrait;

public class RetraitModel {

	private Retrait retrait;

	public RetraitModel() {
		// TODO Auto-generated constructor stub
	}

	public RetraitModel(Retrait retrait) {
		super();
		this.retrait = retrait;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "RetraitModel [retrait=" + retrait + "]";
	}

}
