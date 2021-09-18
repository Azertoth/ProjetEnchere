package fr.formation.projet.enchere.bll.impl;

import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.bll.ArticleVenduManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.ArticleVendu;
import fr.formation.projet.enchere.dal.ArticleVenduDAO;
import fr.formation.projet.enchere.dal.DAOFact;
import fr.formation.projet.enchere.dal.exception.DALException;

public class ArticleVenduManagerImpl implements ArticleVenduManager {
	ArticleVenduDAO dao = DAOFact.getArticleVenduDAO();
	List<ArticleVendu> lstUtilisateur = new ArrayList<ArticleVendu>();
	BLLException exception = new BLLException();

	@Override
	public boolean insert(ArticleVendu av) throws BLLException {
		boolean flag = false;
		try {
			dao.insert(av);
			
			flag = true;
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de l'insertion d'article");

		}
		return flag;
	}

	@Override
	public List<ArticleVendu> select() throws BLLException {
		List<ArticleVendu> list = new ArrayList<ArticleVendu>();
		try {
			list = dao.selectAll();

		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de la selection des articles");

		}
		return list;
	}

	@Override
	public boolean update(ArticleVendu t) throws BLLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(ArticleVendu t) throws BLLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArticleVendu selectByName(String nomArticle) throws BLLException {
		ArticleVendu av = null;
		try {
			av = dao.selectByName(nomArticle);
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de la selection des articles par Nom d'article");

		}
		return av;
	}

	@Override
	public void insertVente(int numArticle, Integer prix, int noVendeur) throws BLLException {
		try {
			dao.insertVente(numArticle, prix, noVendeur);
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de l'insertion de l'enchère");
		}
	}
}
