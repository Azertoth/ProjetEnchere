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
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager manager = EnchereTrocManagerSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/modifprofil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String screen = "/WEB-INF/jsp/modifprofil.jsp";
		UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");

		if (request.getParameter("modifProfil") != null) {
			screen = "/WEB-INF/jsp/modifprofil.jsp";

		}

		if (request.getParameter("supprCompte") != null) {
			try {
				manager.delete(model.getUser());
				screen = "/WEB-INF/jsp/accueil.jsp";
				model.setEstConnecte(false);

			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("erreurSupp", "Suppression non effectuée");
			}

		}

		if (request.getParameter("enregistrer") != null) {
			if (request.getParameter("mdpa").equals(model.getUser().getMotDePasse())) {
				request.setAttribute("messageMdpErreur", "");
				UtilisateurModel model2 = new UtilisateurModel(new Utilisateur());

				model2.getUser().setPseudo(request.getParameter("pseudo"));
				model2.getUser().setNom(request.getParameter("nom"));
				model2.getUser().setPrenom(request.getParameter("prenom"));
				model2.getUser().setEmail(request.getParameter("email"));
				model2.getUser().setTelephone(request.getParameter("tel"));
				model2.getUser().setRue(request.getParameter("rue"));
				model2.getUser().setCodePostal(request.getParameter("cp"));
				model2.getUser().setVille(request.getParameter("ville"));
				model2.getUser().setMotDePasse(request.getParameter("nmdp"));
				model2.getUser().setConfirmation(request.getParameter("confirm"));
				model2.getUser().setNoUtilisateur(model.getUser().getNoUtilisateur());
				try {
					boolean flag = manager.update(model2.getUser(), model);
					if (flag) {
						screen = "/WEB-INF/jsp/profil.jsp";
						model.getUser().setPseudo(model2.getUser().getPseudo());
						model.getUser().setNom(model2.getUser().getNom());
						model.getUser().setPrenom(model2.getUser().getPrenom());
						model.getUser().setEmail(model2.getUser().getEmail());
						model.getUser().setTelephone(model2.getUser().getTelephone());
						model.getUser().setRue(model2.getUser().getRue());
						model.getUser().setCodePostal(model2.getUser().getCodePostal());
						model.getUser().setVille(model2.getUser().getVille());
						if (!"".equals(model2.getUser().getMotDePasse())) {
							model.getUser().setMotDePasse(model2.getUser().getMotDePasse());
						}

					}
				} catch (BLLException e) {
					e.printStackTrace();
				}

			} else {
				request.setAttribute("messageMdpErreur", "Mauvais mot de passe");
			}
		}
		if (request.getParameter("eniEnchere") != null) {

			screen = "/WEB-INF/jsp/accueil.jsp";

		}

		// A FAIRE CONTEXTE APPLICATIF

		if (request.getParameter("vendeur") != null) {
			screen = "/WEB-INF/jsp/autreprofil.jsp";
			try {
				// TODO : rendre dynamique plus tard
				UtilisateurModel modelBis = new UtilisateurModel(
						manager.selectByPseudo(request.getParameter("vendeur")));

				request.getSession().setAttribute("modelBis", modelBis);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("erreurProfil", "Ce profil n'existe pas");
			}

		}

		if (request.getParameter("retourProfil") != null) {
			screen = "/WEB-INF/jsp/accueil.jsp";

		}

		request.getSession().setAttribute("model", model);
		request.getRequestDispatcher(screen).forward(request, response);
	}

}
