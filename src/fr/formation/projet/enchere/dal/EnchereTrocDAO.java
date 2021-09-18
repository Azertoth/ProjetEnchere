package fr.formation.projet.enchere.dal;

import java.util.List;

import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.exception.DALException;

public interface EnchereTrocDAO<T> {
	// Create
	public void insert(T t) throws DALException;

	// Read
	public List<T> selectAll() throws DALException;

	// Update
	public void update(T t) throws DALException;

	// Delete
	public void delete(T t) throws DALException;

	// TODO à mettre sur l'interface utilisateur DAO
	public Utilisateur selectByPseudo(String pseudo) throws DALException;

}
