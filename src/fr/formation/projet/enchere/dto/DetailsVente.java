package fr.formation.projet.enchere.dto;

import java.time.LocalDateTime;

/**
 * 
 * DTO : Data Transfert Object Objet de transfert de données Dans le but de
 * simplifier les transferts de données entre les sous-systèmes.
 *
 */

public class DetailsVente {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private Integer miseAPrix;
	private String rue;
	private String code_postal;
	private String ville;
	private int noCategorie;
	private String libelle;
	private Integer montant_enchere;
	private int noUtilisateurVendeur;
	private String pseudoVendeur;
	private int noUtilisateurAcheteur;
	private String pseudoAcheteur;
	private Integer creditAcheteur = 0;

	public DetailsVente() {

	}

	public DetailsVente(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Integer miseAPrix, String rue, String code_postal, String ville,
			int noCategorie, String libelle, Integer montant_enchere, int noUtilisateurVendeur, String pseudoVendeur,
			int noUtilisateurAcheteur, String pseudoAcheteur, Integer creditAcheteur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.montant_enchere = montant_enchere;
		this.noUtilisateurVendeur = noUtilisateurVendeur;
		this.pseudoVendeur = pseudoVendeur;
		this.noUtilisateurAcheteur = noUtilisateurAcheteur;
		this.pseudoAcheteur = pseudoAcheteur;
		this.creditAcheteur = creditAcheteur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {

		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public int getNoUtilisateurVendeur() {
		return noUtilisateurVendeur;
	}

	public void setNoUtilisateurVendeur(int noUtilisateurVendeur) {
		this.noUtilisateurVendeur = noUtilisateurVendeur;
	}

	public String getPseudoVendeur() {
		return pseudoVendeur;
	}

	public void setPseudoVendeur(String pseudoVendeur) {
		this.pseudoVendeur = pseudoVendeur;
	}

	public int getNoUtilisateurAcheteur() {
		return noUtilisateurAcheteur;
	}

	public void setNoUtilisateurAcheteur(int noUtilisateurAcheteur) {
		this.noUtilisateurAcheteur = noUtilisateurAcheteur;
	}

	public String getPseudoAcheteur() {
		return pseudoAcheteur;
	}

	public void setPseudoAcheteur(String pseudoAcheteur) {
		this.pseudoAcheteur = pseudoAcheteur;
	}

	public Integer getCreditAcheteur() {
		return creditAcheteur;
	}

	public void setCreditAcheteur(Integer creditAcheteur) {
		this.creditAcheteur = creditAcheteur;
	}

	@Override
	public String toString() {
		return "DetailsVente [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", noCategorie="
				+ noCategorie + ", libelle=" + libelle + ", montant_enchere=" + montant_enchere
				+ ", noUtilisateurVendeur=" + noUtilisateurVendeur + ", pseudoVendeur=" + pseudoVendeur
				+ ", noUtilisateurAcheteur=" + noUtilisateurAcheteur + ", pseudoAcheteur=" + pseudoAcheteur
				+ ", creditAcheteur=" + creditAcheteur + "]";
	}

}
