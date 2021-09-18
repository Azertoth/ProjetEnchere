package fr.formation.projet.enchere.bll;

import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Retrait;

public interface RetraitManager extends EnchereTrocManager<Retrait> {

	public Retrait selectByNoArticle(Integer noArticle) throws BLLException;

}
