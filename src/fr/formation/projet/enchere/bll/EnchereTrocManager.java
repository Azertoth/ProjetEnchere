package fr.formation.projet.enchere.bll;

import java.util.List;

import fr.formation.projet.enchere.bll.exception.BLLException;

public interface EnchereTrocManager<T> {
	public boolean insert(T t) throws BLLException;

	public List<T> select() throws BLLException;

	public boolean update(T t) throws BLLException;

	public void delete(T t) throws BLLException;

}
