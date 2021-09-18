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
import fr.formation.projet.enchere.dal.DAOFact;
import fr.formation.projet.enchere.dal.EnchereTrocDAO;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

/**
 * Servlet implementation class LoginServlet
 * 
 * @author lluthy/fsurin/aweggen
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereTrocDAO<Utilisateur> dao = DAOFact.getUtilisateurDAO();
	UtilisateurManager manager = EnchereTrocManagerSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
		String screen = "/WEB-INF/jsp/login.jsp";
		String id = request.getParameter("id");
		String mdp = request.getParameter("password");

		if (model == null) {
			model = new UtilisateurModel(new Utilisateur());
		}

		// quand on appuie sur le bouton ENI-Enchere
		if (request.getParameter("eniEnchere") != null) {
			screen = "/WEB-INF/jsp/accueil.jsp";
		}

		// quand on appuie sur le bouton "Connexion"
		if (request.getParameter("connection") != null) {

			// on attribue l'id et le mdp à la session
			request.getSession().setAttribute("idS", id);
			request.getSession().setAttribute("mdpS", mdp);

			try {
				if (manager.isAMail(id)) {

					// si l'id est un mail
					for (Utilisateur item : manager.select()) {
						if (item.getMotDePasse().equals(mdp)) {
							if (item.getEmail().equals(id)) {
								screen = "/WEB-INF/jsp/accueil.jsp";
								model.setUser(item);
								model.setEstConnecte(true);
								model.setSession(request.getSession());
								continue;
							} else {
								request.setAttribute("loginError", "Cet identifiant n'existe pas");
							}
						} else {

							request.setAttribute("MdpError", "Mot de passe incorrect");
						}

					}
				} else {

					// si l'id est un pseudo
					System.out.println("c'est un pseudo !!!");

					for (Utilisateur item : manager.select()) {
						if (item.getMotDePasse().equals(mdp)) {
							if (item.getPseudo().equals(id)) {

								screen = "/WEB-INF/jsp/accueil.jsp";
								System.out.println(item);
								model.setUser(item);
								model.setEstConnecte(true);
								model.setSession(request.getSession());
								continue;
							} else {
								request.setAttribute("loginError", "Cet identifiant n'existe pas");
							}
						} else {
							request.setAttribute("MdpError", "Mot de passe incorrect");
						}
					}

				}
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}
		if (request.getParameter("creerCompte") != null) {
			screen = "/WEB-INF/jsp/inscription.jsp";
		}
		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher(screen).forward(request, response);
	}

}
