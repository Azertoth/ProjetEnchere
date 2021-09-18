package fr.formation.projet.enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.projet.enchere.bll.EnchereTrocManagerSingl;
import fr.formation.projet.enchere.bll.UtilisateurManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager manager = EnchereTrocManagerSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
		String screen = "/WEB-INF/jsp/inscription.jsp";

		if (request.getParameter("eniEnchere") != null) {
			screen = "/WEB-INF/jsp/accueil.jsp";
		}

		if (request.getParameter("annulerInscription") != null) {
			screen = "/WEB-INF/jsp/accueil.jsp";
		}

		if (request.getParameter("creerCompte") != null) {
			model = new UtilisateurModel(new Utilisateur());
			model.getUser().setPseudo(request.getParameter("pseudo"));
			model.getUser().setNom(request.getParameter("nom"));
			model.getUser().setPrenom(request.getParameter("prenom"));
			model.getUser().setEmail(request.getParameter("email"));
			model.getUser().setTelephone(request.getParameter("tel"));
			model.getUser().setRue(request.getParameter("rue"));
			model.getUser().setCodePostal(request.getParameter("cp"));
			model.getUser().setVille(request.getParameter("ville"));
			model.getUser().setMotDePasse(request.getParameter("mdp"));
			model.getUser().setConfirmation(request.getParameter("confirm"));
			System.out.println(model.getUser());
			try {
				boolean flag = manager.insert(model.getUser());
				if (flag) {
					screen = "/WEB-INF/jsp/accueil.jsp";
					model.setEstConnecte(true);
					model.setSession(request.getSession());
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}

		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher(screen).forward(request, response);
	}

}
