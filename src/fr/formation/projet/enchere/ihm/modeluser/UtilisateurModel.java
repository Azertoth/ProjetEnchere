package fr.formation.projet.enchere.ihm.modeluser;

import javax.servlet.http.HttpSession;

import fr.formation.projet.enchere.bo.Utilisateur;

public class UtilisateurModel {
	private Utilisateur user;
	private boolean estConnecte = false;
	private HttpSession session;

	public boolean isEstConnecte() {
		return estConnecte;
	}

	public void setEstConnecte(boolean estConnecte) {
		this.estConnecte = estConnecte;
	}

	public UtilisateurModel() {
		super();
		this.estConnecte = false;
	}

	public UtilisateurModel(Utilisateur user) {
		super();
		this.user = user;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "UtilisateurModel [user=" + user + "]";
	}

}
