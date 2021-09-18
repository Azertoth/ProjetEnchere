package fr.formation.projet.enchere.bll;

import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.ArticleVendu;

public interface ArticleVenduManager extends EnchereTrocManager<ArticleVendu> {

	ArticleVendu selectByName(String nomArticle) throws BLLException;

	void insertVente(int noArticle, Integer prixVente, int noVendeur) throws BLLException;

}
