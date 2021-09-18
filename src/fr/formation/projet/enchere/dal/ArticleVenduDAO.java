package fr.formation.projet.enchere.dal;

import java.util.List;

import fr.formation.projet.enchere.bo.ArticleVendu;
import fr.formation.projet.enchere.dal.exception.DALException;

public interface ArticleVenduDAO {

	// Create
	public void insert(ArticleVendu av) throws DALException;

	// Read
	public List<ArticleVendu> selectAll() throws DALException;

	// Update
	public void update(ArticleVendu av) throws DALException;

	// Delete
	public void delete(ArticleVendu av) throws DALException;

	public ArticleVendu selectByName(String nom) throws DALException;

	public void insertVente(int numArticle, Integer prix, int noVendeur) throws DALException;

}
