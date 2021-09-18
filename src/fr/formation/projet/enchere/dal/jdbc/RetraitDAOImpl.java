package fr.formation.projet.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.bo.Retrait;
import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.RetraitDAO;
import fr.formation.projet.enchere.dal.exception.DALException;

public class RetraitDAOImpl implements RetraitDAO {
	private final static String INSERT = " INSERT INTO RETRAITS(no_article,rue, code_postal, ville) VALUES(?,?, ?, ?)";
	private final static String SELECT = " SELECT no_article, rue, code_postal, ville FROM RETRAITS";
	private final static String SELECTBYNOARTICLE = " SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article=?";
// 	fonctions inutilisées pour le moment	
//	private final static String UPDATE = " UPDATE rue=? code_postal=? ville=? WHERE no_article=?";
//	private final static String DELETE = " DELETE FROM RETRAITS WHERE no_article=?";

	@Override
	public void insert(Retrait retrait) {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCode_postal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Probleme SQL, c'est po bien ");
			e.printStackTrace();
		}

	}

	@Override
	public List<Retrait> selectAll() {
		List<Retrait> lstRetrait = new ArrayList<>();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Retrait retrait = new Retrait();
				retrait.setNoArticle(rs.getInt("no_article"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				lstRetrait.add(retrait);
			}
		} catch (SQLException e) {
			System.out.println("Probleme SQL, c'est la me*********de");
			e.printStackTrace();
		}

		return lstRetrait;
	}

	@Override
	public Retrait selectByNoArticle(Integer noArticle) {
		Retrait retrait = new Retrait();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTBYNOARTICLE);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				retrait.setNoArticle(rs.getInt("no_article"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));

			}
		} catch (SQLException e) {
			System.out.println("RETRAIT DAO IMPL - Probleme SQL SelectByNoARticle");
			e.printStackTrace();
		}

		return retrait;
	}

//--------------------------------------------- FONCTIONS UNITILISEES POUR LE MOMENT -------------------------------------------------//
	@Override
	public void update(Retrait retrait) {
//		try (Connection con = JdbcTools.getConnection()){
//			PreparedStatement stmt = con.prepareStatement(UPDATE);
//			stmt.setInt(1,  retrait.getNoArticle());
//			stmt.setString(2, retrait.getRue());
//			stmt.setString(3,  retrait.getCode_postal());
//			stmt.setString(4, retrait.getVille());
//			stmt.executeUpdate();		
//		}catch (SQLException e) {
//			System.out.println("Problème SQL, change moi ca tout de suite");
//			e.printStackTrace();
//		}	
	}

	@Override
	public void delete(Retrait retrait) {
//		try(Connection con = JdbcTools.getConnection()){
//			PreparedStatement stmt = con.prepareStatement(DELETE);
//			stmt.setInt(1,  retrait.getNoArticle());
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
}
