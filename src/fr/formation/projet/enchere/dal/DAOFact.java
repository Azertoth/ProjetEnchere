package fr.formation.projet.enchere.dal;

import fr.formation.projet.enchere.dal.jdbc.ArticleVenduDAOImpl;
import fr.formation.projet.enchere.dal.jdbc.RetraitDAOImpl;
import fr.formation.projet.enchere.dal.jdbc.UtilisateurDAOImpl;

public class DAOFact {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
}
