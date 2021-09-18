package fr.formation.projet.enchere.dal;

import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.exception.DALException;

public interface UtilisateurDAO extends EnchereTrocDAO<Utilisateur> {

	public void updateCredit(Utilisateur user, Integer credit) throws DALException;

}
