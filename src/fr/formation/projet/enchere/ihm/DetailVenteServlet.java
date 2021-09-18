package fr.formation.projet.enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.projet.enchere.bll.ArticleVenduManager;
import fr.formation.projet.enchere.bll.EnchereTrocManagerSingl;
import fr.formation.projet.enchere.bll.UtilisateurManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bll.impl.DetailsVenteManager;
import fr.formation.projet.enchere.dto.DetailsVente;
import fr.formation.projet.enchere.dto.DtoManagerImpl;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UtilisateurManager managerU = EnchereTrocManagerSingl.getInstance();
	ArticleVenduManager managerArticle = EnchereTrocManagerSingl.getInstanceAV();
	DetailsVenteManager managerDetails = EnchereTrocManagerSingl.getInstanceDetails();

	DtoManagerImpl managerAV = new DtoManagerImpl();
//	DtoModel modelArticle = new DtoModel(new DetailsVente(0, "", "", LocalDateTime.now(), LocalDateTime.now(), 0, "",
//			"", "", 0, "", 0, 0, "", 0, "", 0));

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailVenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/detailvente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String screen = "/WEB-INF/jsp/detailvente.jsp";
			UtilisateurModel model = (UtilisateurModel) request.getSession().getAttribute("model");
			DetailsVente articleV = (DetailsVente) this.getServletContext().getAttribute("articleV");

			if (request.getParameter("encherir") != null) {
				Integer proposition = Integer.parseInt(request.getParameter("proposition"));
				Integer credit = model.getUser().getCredit();

				// verification si la proposition est supérieure à la valeur actuelle
				if (proposition > articleV.getMontant_enchere()) {

					// vérifier si l'acheteur a assez de crédit
					if (managerDetails.peutAcheter(credit, proposition)) {

						// déduire le prix des crédits
						model.getUser().setCredit(credit - proposition);

						// rendre les crédits à l'ancien acheteur
						managerDetails.ajouterCredit(articleV.getPseudoAcheteur(), articleV.getCreditAcheteur());

						// mise à jour des infos de l'article
						articleV.setNoUtilisateurAcheteur((model.getUser().getNoUtilisateur()));
						articleV.setMontant_enchere(proposition);
						articleV.setPseudoAcheteur(model.getUser().getPseudo());

						// mise à jour dans la base de données du nouveau acheteur
						managerU.updateCredit(model.getUser(), model.getUser().getCredit());

						// mise a jour de l'article en base de données
						articleV.setCreditAcheteur(proposition);
						managerDetails.insertVente(articleV.getNoArticle(), proposition,
								articleV.getNoUtilisateurAcheteur());

						// renvoi sur l'accueil
						screen = "/WEB-INF/jsp/accueil.jsp";
						request.setAttribute("messageVente", "La proposition a bien été acceptée !");
					} else {
						request.setAttribute("prixIncorrect", "Vous n'avez pas assez de crédit");
					}
				} else {
					request.setAttribute("prixIncorrect", "le montant n'est pas possible");
				}
			}

			this.getServletContext().setAttribute("articleV", articleV);
			request.getRequestDispatcher(screen).forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

}
