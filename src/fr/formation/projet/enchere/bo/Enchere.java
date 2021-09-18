package fr.formation.projet.enchere.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	private Integer montant_enchere;
	private Utilisateur encherit;
	private ArticleVendu concerne;

	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(LocalDate dateEnchere, Integer montant_enchere, Utilisateur encherit, ArticleVendu concerne) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.encherit = encherit;
		this.concerne = concerne;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getEncherit() {
		return encherit;
	}

	public void setEncherit(Utilisateur encherit) {
		this.encherit = encherit;
	}

	public ArticleVendu getConcerne() {
		return concerne;
	}

	public void setConcerne(ArticleVendu concerne) {
		this.concerne = concerne;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", encherit=" + encherit
				+ ", concerne=" + concerne + "]";
	}

}
