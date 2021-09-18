package fr.formation.projet.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.bo.ArticleVendu;
import fr.formation.projet.enchere.dal.ArticleVenduDAO;
import fr.formation.projet.enchere.dal.exception.DALException;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	public static final String INSERT = "INSERT INTO ARTICLES_VENDUS  (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?, ?, ?, ? ,? ,? ,?)";
	public static final String SELECTBYNAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ARTICLES_VENDUS WHERE nom_article = ?";
	public static final String SELECT = "";
	public static final String INSERTVENTE = "INSERT INTO ENCHERES ( montant_enchere, no_utilisateur, no_article, date_enchere) VALUES (?,?,?,?) ";

	@Override
	public void insert(ArticleVendu av) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, av.getNomArticle());
			stmt.setString(2, av.getDescription());
			stmt.setTimestamp(3, java.sql.Timestamp.valueOf(av.getDateDebutEncheres()));
			stmt.setTimestamp(4, java.sql.Timestamp.valueOf(av.getDateFinEncheres()));
			stmt.setInt(5, av.getMiseAPrix());
			stmt.setInt(6, av.getMiseAPrix());
			stmt.setInt(7, av.getVendeur().getNoUtilisateur());
			stmt.setInt(8, av.getCategorieArticle().getNoCategorie());

			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					av.setNoArticle(rs.getInt(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme Insert SQL pour un article ");
		}

	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> lstArticles = new ArrayList<>();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNomArticle(rs.getString("nomArticle"));
				article.setDescription(rs.getString("description"));
				article.setMiseAPrix(rs.getInt("miseAPrix"));
				article.setPrixVente(rs.getInt("prixVente"));
				article.setEtatVente(rs.getString("etatVente"));
				lstArticles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme SQL : Requête SELECTALL ArticleVENDU");

		}
		return lstArticles;
	}

	@Override
	public void update(ArticleVendu av) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ArticleVendu av) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArticleVendu selectByName(String nom) throws DALException {
		ArticleVendu av = new ArticleVendu();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTBYNAME);
			stmt.setString(1, av.getNomArticle());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				av.setNoArticle(rs.getInt("no_article"));
				av.setNomArticle(rs.getString("nom_article"));
				av.setDescription(rs.getString("description"));
				av.setDateDebutEncheres(LocalDateTime.parse((CharSequence) rs.getTimestamp("date_debut_encheres")));
				av.setDateFinEncheres(LocalDateTime.parse((CharSequence) rs.getTimestamp("date_fin_encheres")));
				av.setMiseAPrix(rs.getInt("prix_initial"));
				av.setPrixVente(rs.getInt("prix_vente"));
				av.getVendeur().setNoUtilisateur(rs.getInt("no_utilisateur"));
				av.getCategorieArticle().setNoCategorie(rs.getInt("no_categorie"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur de select DAO");
		}
		return av;
	}

	@Override
	public void insertVente(int numArticle, Integer prix, int noVendeur) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERTVENTE);
			stmt.setInt(1, prix);
			stmt.setInt(2, noVendeur);
			stmt.setInt(3, numArticle);
			stmt.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur de select DAO");
		}
	}
}
