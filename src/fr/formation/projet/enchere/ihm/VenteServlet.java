package fr.formation.projet.enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.projet.enchere.bll.ArticleVenduManager;
import fr.formation.projet.enchere.bll.EnchereTrocManagerSingl;
import fr.formation.projet.enchere.bll.RetraitManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.ArticleVendu;
import fr.formation.projet.enchere.bo.Categorie;
import fr.formation.projet.enchere.bo.Retrait;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

/**
 * Servlet implementation class VenteServlet
 */
@WebServlet("/VenteServlet")
public class VenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager manager = EnchereTrocManagerSingl.getInstanceAV();
	RetraitManager managerRetrait = EnchereTrocManagerSingl.getInstanceRetrait();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/nouvellevente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String screen = "/WEB-INF/jsp/nouvellevente.jsp";
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
		ArticleVendu article = new ArticleVendu("", "", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 0, 0,
				model.getUser(), new Categorie(0));
		Retrait retrait = new Retrait("", "", "", 0);

		if (request.getParameter("enregistrer") != null) {
			article.setNomArticle(request.getParameter("article"));
			article.setDescription(request.getParameter("description"));

			article.getCategorieArticle().setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
			article.setMiseAPrix(Integer.parseInt(request.getParameter("miseaprix")));
			article.setDateDebutEncheres(LocalDateTime.parse(request.getParameter("datedebut")));
			article.setDateFinEncheres(LocalDateTime.parse(request.getParameter("datefin")));
			retrait.setRue(request.getParameter("rue"));
			retrait.setCode_postal(request.getParameter("cp"));
			retrait.setVille(request.getParameter("ville"));

			try {
				manager.insert(article);
				retrait.setNoArticle(article.getNoArticle());
				managerRetrait.insert(retrait);

			} catch (BLLException e) {
				e.printStackTrace();
			}
			screen = "/WEB-INF/jsp/accueil.jsp";
			request.setAttribute("messageVente", "L'enregistrement s'est bien effectué");
		}

		if (request.getParameter("annuler") != null) {
			screen = "/WEB-INF/jsp/accueil.jsp";
			request.setAttribute("messageVente", "Création de la nouvelle vente annulée");
		}

		request.getRequestDispatcher(screen).forward(request, response);
	}

}
