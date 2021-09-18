package fr.formation.projet.enchere.bll;

import fr.formation.projet.enchere.bll.impl.ArticleVenduManagerImpl;
import fr.formation.projet.enchere.bll.impl.DetailsVenteManager;
import fr.formation.projet.enchere.bll.impl.RetraitManagerImpl;
import fr.formation.projet.enchere.bll.impl.UtilisateurManagerImpl;

public class EnchereTrocManagerSingl {

	public static UtilisateurManager instanceUti;
	public static ArticleVenduManager instanceAV;
	public static RetraitManager instanceRetrait;
	public static DetailsVenteManager instanceDetails;

	public static UtilisateurManager getInstance() {
		if (instanceUti == null) {
			instanceUti = new UtilisateurManagerImpl();
		}
		return instanceUti;
	}

	public static ArticleVenduManager getInstanceAV() {
		if (instanceAV == null) {
			instanceAV = new ArticleVenduManagerImpl();
		}
		return instanceAV;
	}

	public static RetraitManager getInstanceRetrait() {
		if (instanceRetrait == null) {
			instanceRetrait = new RetraitManagerImpl();
		}
		return instanceRetrait;
	}

	public static DetailsVenteManager getInstanceDetails() {
		if (instanceDetails == null) {
			instanceDetails = new DetailsVenteManager();
		}
		return instanceDetails;
	}

}
