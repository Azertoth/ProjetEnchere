package fr.formation.projet.enchere.bll;

import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.exception.DALException;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

public interface UtilisateurManager extends EnchereTrocManager<Utilisateur> {

	public boolean isAlphanumeric(String str);

	public boolean isAMail(String str);

	public boolean update(Utilisateur utilisateur, UtilisateurModel model) throws BLLException;

	public Utilisateur selectByPseudo(String str) throws BLLException;

	public void updateCredit(Utilisateur user, Integer credit) throws BLLException;

}
