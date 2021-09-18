package fr.formation.projet.enchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.projet.enchere.bll.ArticleVenduManager;
import fr.formation.projet.enchere.bll.EnchereTrocManagerSingl;
import fr.formation.projet.enchere.bll.RetraitManager;
import fr.formation.projet.enchere.bll.UtilisateurManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.ArticleVendu;
import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.exception.DALException;
import fr.formation.projet.enchere.dto.DetailsVente;
import fr.formation.projet.enchere.dto.DtoManagerImpl;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet({ "/AccueilServlet" })
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurModel model;
	List<ArticleVendu> list;
	ArticleVendu articleVendu;
	ArticleVenduManager manager = EnchereTrocManagerSingl.getInstanceAV();
	UtilisateurManager managerUser = EnchereTrocManagerSingl.getInstance();
	RetraitManager managerRetrait = EnchereTrocManagerSingl.getInstanceRetrait();
	DtoManagerImpl managerAV = new DtoManagerImpl();
	List<DetailsVente> listEnchere = null;
	List<DetailsVente> listfull = null;

	public AccueilServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Initialisation de la liste des ventes en cours (lancement de la servlet)
		 * 
		 */

		// List<DetailsVente> listfull = null;

		listfull = managerAV.venteEnCours();

		request.setAttribute("liste", listfull);
		request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String screen = "/WEB-INF/jsp/accueil.jsp";
		List<DetailsVente> filter = null;

		// Déconnexion inactivité 5 minutes
		if(model != null) {
		if (model.getSession() != request.getSession()) {
			model.setEstConnecte(false);
			model = null;
		}}
		
		if (model == null) {
			model = new UtilisateurModel(new Utilisateur());

		} else {
			model = (UtilisateurModel) request.getSession().getAttribute("model");
		}

		if (model.isEstConnecte() == false || model == null) {
			// ---------- ACCUEIL HORS CONNEXION ---------------

			if (request.getParameter("seConnecter") != null) {
				screen = "/WEB-INF/jsp/login.jsp";
			}
		} else {
			// ---------- ACCUEIL CONNECTE ---------------

			if (request.getParameter("deconnexion") != null) {
				request.getSession().setAttribute("model", null);
				model.setEstConnecte(false);
				request.getSession().invalidate();
				screen = "/WEB-INF/jsp/accueil.jsp";
			}

			// ---------- FILTRE MES VENTES ---------------

			/**
			 * Affichage de la liste "Mes ventes en cours" suivant l'utilisateur en cours.
			 * 
			 */
			if (request.getParameter("venteCours") != null) {
				filter = managerAV.mesVentes(model.getUser().getPseudo());
				request.setAttribute("liste", filter);
			}
			/**
			 * Affichage de la liste "Mes ventes terminées" suivant l'utilisateur en cours.
			 * 
			 */
			if (request.getParameter("venteTermine") != null) {
				filter = managerAV.mesVentesTerminees(model.getUser().getPseudo());
				request.setAttribute("liste", filter);
			}
			/**
			 * Affichage de la liste "Mes ventes non débutées" suivant l'utilisateur en
			 * cours.
			 * 
			 */
			if (request.getParameter("venteNonRemporte") != null) {
				filter = managerAV.mesVentesNonDebutees(model.getUser().getPseudo());
				request.setAttribute("liste", filter);

			}

			// ---------- FILTRE ACHATS ---------------
			/**
			 * Affichage de la liste "Mes ventes non débutées" suivant l'utilisateur en
			 * cours.
			 * 
			 */
			if (request.getParameter("ouverte") != null) {
				filter = managerAV.encheresOuvertes(model.getUser().getPseudo());
				request.setAttribute("liste", filter);
			}
			if (request.getParameter("encours") != null) {
				filter = managerAV.mesEncheresEnCours(model.getUser().getPseudo());
				request.setAttribute("liste", filter);
			}
			if (request.getParameter("remporte") != null) {
				filter = managerAV.mesEncheresRemportees(model.getUser().getPseudo());
				request.setAttribute("liste", filter);
			}

			// ---------- BOUTON DE NAVIGATION ---------------

			if (request.getParameter("afficherProfil") != null) {
				screen = "/WEB-INF/jsp/profil.jsp";
			}

			if (request.getParameter("eniEnchere") != null) {
				screen = "/WEB-INF/jsp/accueil.jsp";
			}

			if (request.getParameter("vendreArt") != null) {
				screen = "/WEB-INF/jsp/nouvellevente.jsp";
			}

			if (request.getParameter("rechercher") != null) {
				screen = "/WEB-INF/jsp/accueil.jsp";
			}

		}

		if (request.getParameter("noArticle") != null) {
			screen = "DetailVenteServlet";
			DetailsVente articleV = managerAV.selectById(Integer.parseInt(request.getParameter("noArticle")));
			this.getServletContext().setAttribute("articleV", articleV);
		}

		if (request.getParameter("vendeur") != null) {
			screen = "/WEB-INF/jsp/autreprofil.jsp";

			Utilisateur utilisateur = null;
			try {
				utilisateur = managerUser.selectByPseudo(request.getParameter("vendeur"));
				this.getServletContext().setAttribute("utilisateur", utilisateur);
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}

		/**
		 * Relance de la liste "vente en cours" disponible hors connexion.
		 * 
		 * 
		 */

		listEnchere = managerAV.venteEnCours();

		// ---------- BOUTON RECHERCHER ---------------

		if (request.getParameter("find") != null && !"".equals(request.getParameter("find"))) {
			try {
				filter = managerAV.find(request.getParameter("find"));

				request.setAttribute("liste", filter);
			} catch (DALException e) {
				e.printStackTrace();
			}

		}

		request.getSession().setAttribute("liste", listEnchere);
		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher(screen).forward(request, response);
	}

}
