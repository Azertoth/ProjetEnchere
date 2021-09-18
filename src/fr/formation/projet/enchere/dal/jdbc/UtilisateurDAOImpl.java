package fr.formation.projet.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.UtilisateurDAO;
import fr.formation.projet.enchere.dal.exception.DALException;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	private final static String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String SELECT = "SELECT no_Utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	private final static String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_Utilisateur=?";
	private final static String UPDATECREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_Utilisateur=?";
	private final static String DELETE = "DELETE FROM UTILISATEURS WHERE no_Utilisateur=?";
	private final static String SELECTPSEUDO = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, no_utilisateur FROM UTILISATEURS WHERE pseudo=?";

	@Override
	public void insert(Utilisateur user) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getCredit());
			stmt.setBoolean(11, user.isAdministrateur());

			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					user.setNoUtilisateur(rs.getInt(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("problème d'insertion d'utilisateur en base de données");
		}

	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> lstUser = new ArrayList<>();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
				lstUser.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme SQL pour la selection de tous les utilisateurs");

		}
		return lstUser;

	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		Utilisateur user = new Utilisateur();
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECTPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme SQL pour la selection d'un utilisateur selon son pseudo");

		}

		return user;
	}

	@Override
	public void update(Utilisateur user) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE);

			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getNoUtilisateur());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL pour la modification d'un utilisateur");
		}

	}

	@Override
	public void delete(Utilisateur user) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, user.getNoUtilisateur());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQl pour la suppression d'un utilisateur");
		}

	}

	@Override
	public void updateCredit(Utilisateur user, Integer credit) throws DALException {
		try (Connection con = JdbcTools.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATECREDIT);
			System.out.println("utilisateur recevant le credit " + user.getNoUtilisateur());
			System.out.println("crédit recu : " + credit);
			stmt.setInt(1, credit);
			stmt.setInt(2, user.getNoUtilisateur());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème SQL dans la mise à jour des crédits d'un utilisateur");
		}

	}
}
