package fr.formation.projet.enchere.dal.exception;

import java.sql.SQLException;

public class DALException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DALException(String message) {
		super(message);
	}

}
