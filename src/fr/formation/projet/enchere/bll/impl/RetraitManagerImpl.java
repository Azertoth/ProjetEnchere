package fr.formation.projet.enchere.bll.impl;

import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.bll.RetraitManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Retrait;
import fr.formation.projet.enchere.dal.DAOFact;
import fr.formation.projet.enchere.dal.RetraitDAO;
import fr.formation.projet.enchere.dal.exception.DALException;

public class RetraitManagerImpl implements RetraitManager {
	RetraitDAO dao = DAOFact.getRetraitDAO();
	BLLException exception = new BLLException();

	@Override
	public boolean insert(Retrait t) throws BLLException {
		boolean estInsere = false;

		try {
			dao.insert(t);
			estInsere = true;
		} catch (Exception e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de l'insertion du retrait");
		}
		return estInsere;
	}

	@Override
	public List<Retrait> select() throws BLLException {
		List<Retrait> list = new ArrayList<Retrait>();
		try {
			list = dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de la selection des retraits");
		}
		return list;
	}

	@Override
	public boolean update(Retrait t) throws BLLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Retrait t) throws BLLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Retrait selectByNoArticle(Integer noArticle) throws BLLException {
		Retrait retrait = null;
		try {

			retrait = dao.selectByNoArticle(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("Problème lors de la selection du retrait par No Article");
		}
		return retrait;
	}

}
