package fr.formation.projet.enchere.dal;

import fr.formation.projet.enchere.bo.Retrait;
import fr.formation.projet.enchere.dal.exception.DALException;

public interface RetraitDAO extends EnchereTrocDAO<Retrait> {

	public Retrait selectByNoArticle(Integer noArticle) throws DALException;

}
