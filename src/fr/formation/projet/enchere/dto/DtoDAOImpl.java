package fr.formation.projet.enchere.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.dal.exception.DALException;
import fr.formation.projet.enchere.dal.jdbc.JdbcTools;

public class DtoDAOImpl {

	private final static String SELECTBYID = "SELECT TOP 1 Articles_VENDUS.no_article AS numArticleVendu,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente, CATEGORIES.no_categorie AS numCategorie,CATEGORIES.libelle AS libelleCat,montant_enchere, ENCHERES.no_utilisateur AS No_ENCHERISSEUR, Uacheteur.pseudo AS PseudoENCHERISSEUR, Uvendeur.pseudo AS PseudoVENDEUR,RETRAITS.rue AS rue,RETRAITS.code_postal AS cpo,RETRAITS.ville AS ville "
			+ "			FROM ARTICLES_VENDUS " + "			INNER JOIN RETRAITS  "
			+ "			ON ARTICLES_VENDUS.no_article = RETRAITS.no_article " + "			INNER JOIN CATEGORIES  "
			+ "			ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " + "			INNER JOIN ENCHERES "
			+ "			ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
			+ "			INNER JOIN UTILISATEURS as Uvendeur  "
			+ "			ON ARTICLES_VENDUS.no_utilisateur = Uvendeur.no_utilisateur "
			+ "			INNER JOIN UTILISATEURS as Uacheteur "
			+ "			ON ENCHERES.no_utilisateur = Uacheteur.no_utilisateur "
			+ "			WHERE Articles_VENDUS.no_article=?" + "			ORDER BY montant_enchere DESC";

	private final static String SELECTALL = "SELECT DISTINCT Articles_VENDUS.no_article, ARTICLES_VENDUS.nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,CATEGORIES.no_categorie AS numCategorie,CATEGORIES.libelle AS libelleCat, Uvendeur.pseudo AS vendeur,ARTICLES_VENDUS.no_utilisateur AS noVendeur, Uacheteur.pseudo AS acheteur, ENCHERES.no_utilisateur AS noAcheteur,    ENCHERES.montant_enchere,RETRAITS.rue AS rue,RETRAITS.code_postal AS cpo,RETRAITS.ville AS ville \r\n"
			+ "FROM ENCHERES\r\n" + "INNER JOIN ARTICLES_VENDUS\r\n"
			+ "ON ARTICLES_VENDUS.no_article = ENCHERES.no_article\r\n" + "INNER JOIN CATEGORIES\r\n"
			+ "ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie\r\n" + "INNER JOIN RETRAITS\r\n"
			+ "ON RETRAITS.no_article = ARTICLES_VENDUS.no_article\r\n" + "INNER JOIN UTILISATEURS as Uvendeur\r\n"
			+ "ON Uvendeur.no_utilisateur = ARTICLES_VENDUS.no_utilisateur\r\n"
			+ "INNER JOIN UTILISATEURS as Uacheteur\r\n" + "ON Uacheteur.no_utilisateur = ENCHERES.no_utilisateur\r\n"
			+ "ORDER BY no_article,montant_enchere DESC";
	private final static String SELECTBUTTON = "SELECT DISTINCT Articles_VENDUS.no_article, ARTICLES_VENDUS.nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,CATEGORIES.no_categorie AS numCategorie,CATEGORIES.libelle AS libelleCat, Uvendeur.pseudo AS vendeur,ARTICLES_VENDUS.no_utilisateur AS noVendeur, Uacheteur.pseudo AS acheteur, ENCHERES.no_utilisateur AS noAcheteur,    ENCHERES.montant_enchere,RETRAITS.rue AS rue,RETRAITS.code_postal AS cpo,RETRAITS.ville AS ville  \r\n"
			+ "			FROM ENCHERES \r\n" + "			INNER JOIN ARTICLES_VENDUS \r\n"
			+ "			ON ARTICLES_VENDUS.no_article = ENCHERES.no_article \r\n"
			+ "			INNER JOIN CATEGORIES \r\n"
			+ "			ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie\r\n"
			+ "			INNER JOIN RETRAITS\r\n" + "			ON RETRAITS.no_article = ARTICLES_VENDUS.no_article\r\n"
			+ "			INNER JOIN UTILISATEURS as Uvendeur\r\n"
			+ "			ON Uvendeur.no_utilisateur = ARTICLES_VENDUS.no_utilisateur\r\n"
			+ "			INNER JOIN UTILISATEURS as Uacheteur \r\n"
			+ "			ON Uacheteur.no_utilisateur = ENCHERES.no_utilisateur \r\n"
			+ "			WHERE nom_article LIKE ?";

	/**
	 * 
	 * @param id
	 * @return Les enregistrements sont dont la valeur d’une colonne (no article)
	 *         contient l'entier présent en paramètre de cette méthode.
	 */
	public DetailsVente selectById(Integer id) {
		DetailsVente dV = null;

		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dV = new DetailsVente();
				dV.setNoArticle(rs.getInt("numArticleVendu"));
				dV.setNomArticle(rs.getString("nom_article"));
				dV.setDescription(rs.getString("description"));
				dV.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				dV.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				dV.setMiseAPrix(rs.getInt("prix_initial"));
				dV.setMontant_enchere(rs.getInt("prix_vente"));
				dV.setNoCategorie(rs.getInt("numCategorie"));
				dV.setLibelle(rs.getString("libelleCat"));
				dV.setCreditAcheteur(rs.getInt("montant_enchere"));
				dV.setNoUtilisateurAcheteur(rs.getInt("No_ENCHERISSEUR"));
				dV.setPseudoAcheteur(rs.getString("PseudoENCHERISSEUR"));
				dV.setRue(rs.getString("rue"));
				dV.setCode_postal(rs.getString("cpo"));
				dV.setVille(rs.getString("ville"));
				dV.setPseudoVendeur(rs.getString("PseudoVENDEUR"));
			}
		} catch (SQLException e) {
			System.out.println("PB DTO DAO ICI");
			e.printStackTrace();
		}

		return dV;
	}

	/**
	 * 
	 * @returnLes Les enregistrements et les paramètres nécessaires à l'affichage
	 *            des cards(accueil) et des objets en vente.
	 * @throws DALException
	 */
	public List<DetailsVente> selectAll() throws DALException {
		List<DetailsVente> lstDV = new ArrayList<>();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				DetailsVente dV = new DetailsVente();
				dV.setNoArticle(rs.getInt("no_article"));
				dV.setNomArticle(rs.getString("nom_article"));
				dV.setDescription(rs.getString("description"));
				dV.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				dV.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				dV.setMiseAPrix(rs.getInt("prix_initial"));
				dV.setMontant_enchere(rs.getInt("prix_vente"));
				dV.setNoCategorie(rs.getInt("numCategorie"));
				dV.setLibelle(rs.getString("libelleCat"));
				dV.setCreditAcheteur(rs.getInt("montant_enchere"));
				dV.setNoUtilisateurAcheteur(rs.getInt("noAcheteur"));
				dV.setPseudoAcheteur(rs.getString("acheteur"));
				dV.setRue(rs.getString("rue"));
				dV.setCode_postal(rs.getString("cpo"));
				dV.setVille(rs.getString("ville"));
				dV.setPseudoVendeur(rs.getString("vendeur"));
				lstDV.add(dV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme SQL * SELECTALL DTO");

		}
		return lstDV;

	}

	/**
	 * 
	 * @param str du boutton
	 * @return Les enregistrements dont la valeur d’une colonne (nom article)
	 *         contient le String en paramètre.
	 * @throws DALException
	 */
	public List<DetailsVente> bouttonRechercher(String str) throws DALException {
		List<DetailsVente> lstDV = new ArrayList<>();
		DetailsVente dV = null;
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTBUTTON);
			System.out.println("Je suis dans la requête le String est " + str);
			stmt.setString(1, "%" + str + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dV = new DetailsVente();
				dV.setNoArticle(rs.getInt("no_article"));
				dV.setNomArticle(rs.getString("nom_article"));
				dV.setDescription(rs.getString("description"));
				dV.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
				dV.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
				dV.setMiseAPrix(rs.getInt("prix_initial"));
				dV.setMontant_enchere(rs.getInt("prix_vente"));
				dV.setNoCategorie(rs.getInt("numCategorie"));
				dV.setLibelle(rs.getString("libelleCat"));
				dV.setCreditAcheteur(rs.getInt("montant_enchere"));
				dV.setNoUtilisateurAcheteur(rs.getInt("noAcheteur"));
				dV.setPseudoAcheteur(rs.getString("acheteur"));
				dV.setRue(rs.getString("rue"));
				dV.setCode_postal(rs.getString("cpo"));
				dV.setVille(rs.getString("ville"));
				dV.setPseudoVendeur(rs.getString("vendeur"));
				lstDV.add(dV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme SQL requête FIND du DTO");

		}

		return lstDV;
	}

}
