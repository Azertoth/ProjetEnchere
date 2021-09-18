package fr.formation.projet.enchere.bll.impl;

import fr.formation.projet.enchere.bll.ArticleVenduManager;
import fr.formation.projet.enchere.bll.EnchereTrocManagerSingl;
import fr.formation.projet.enchere.bll.UtilisateurManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Utilisateur;

public class DetailsVenteManager {
	UtilisateurManager managerU = EnchereTrocManagerSingl.getInstance();
	ArticleVenduManager managerArticle = EnchereTrocManagerSingl.getInstanceAV();

	// vérifier si l'acheteur a assez de crédit
	public boolean peutAcheter(Integer credit, Integer proposition) {
		boolean peutAcheter = false;
		if (credit >= proposition) {
			peutAcheter = true;
		}

		return peutAcheter;
	}

	// rendre les crédits à l'ancien acheteur
	public void ajouterCredit(String pseudo, Integer credit) {
		Utilisateur user;
		try {
			user = managerU.selectByPseudo(pseudo);
			user.setCredit(user.getCredit() + credit);
			// mise à jour dans la base de données de l'ancien acheteur
			managerU.updateCredit(user, user.getCredit());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertVente(Integer noArticle, Integer proposition, Integer noUtilisateur) {
		try {
			managerArticle.insertVente(noArticle, proposition, noUtilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
