package fr.formation.projet.enchere.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.formation.projet.enchere.dal.exception.DALException;

public class DtoManagerImpl {

	DtoDAOImpl dao = new DtoDAOImpl();

	public DetailsVente selectById(Integer id) {
		return dao.selectById(id);

	}

	public List<DetailsVente> selectAll() throws DALException {
		return dao.selectAll();

	}

	public List<DetailsVente> venteEnCours() {

		List<DetailsVente> listDao = null;
		List<DetailsVente> list = null;
		try {
			listDao = dao.selectAll();
			list = listDao;
			for (int i = 0; i < listDao.size(); i++) {
				for (int j = 0; j < listDao.size(); j++) {

					if (listDao.get(i).getNoArticle() == listDao.get(j).getNoArticle() && j != i) {
						if (listDao.get(i).getMontant_enchere() > listDao.get(j).getMontant_enchere()) {
							list.remove(j);
						} else {
							list.remove(i);
						}

					}

				}

			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 
	 * @param id
	 * @return List d'objets de type DetailsVente des ventes en cours suivant le
	 *         paramètre ID
	 */
	public List<DetailsVente> mesVentes(String pseudo) {
		System.out.println(pseudo);
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream().filter(
				(a -> ((pseudo).equals(a.getPseudoVendeur()))&& a.getDateDebutEncheres().isBefore(LocalDateTime.now()) && a.getDateFinEncheres().isAfter(LocalDateTime.now() )))
				.collect(Collectors.toList());

		return filter;
	}

	public List<DetailsVente> mesVentesNonDebutees(String pseudo) {
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream().filter(
				(a -> ((pseudo).equals(a.getPseudoVendeur()))  && a.getDateDebutEncheres().isAfter(LocalDateTime.now())))
				.collect(Collectors.toList());

		return filter;
	}

	public List<DetailsVente> mesVentesTerminees(String pseudo) {
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream().filter(
				(a -> ((pseudo).equals(a.getPseudoVendeur())) && a.getDateFinEncheres().isBefore(LocalDateTime.now())))
				.collect(Collectors.toList());

		return filter;
	}

	// ----- FILTRE VENTE -----
	/**
	 * @param pseudo
	 * @return liste des encheres ouvertes
	 */
	// doublon avec venteEnCours
	public List<DetailsVente> encheresOuvertes(String pseudo) {
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream().filter(
				(a -> ((pseudo).equals(a.getPseudoVendeur())) && a.getDateFinEncheres().isAfter(LocalDateTime.now())))
				.collect(Collectors.toList());

		return filter;
	}

	/**
	 * 
	 * @param pseudo
	 * @return liste des encheres en cours suivant l'utilisateur connecté.
	 */
	public List<DetailsVente> mesEncheresEnCours(String pseudo) {
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream()
				.filter((a -> ((pseudo).equals(a.getPseudoAcheteur()))
						&& a.getDateFinEncheres().isBefore(LocalDateTime.now())
						&& a.getDateDebutEncheres().isBefore(LocalDateTime.now())))
				.collect(Collectors.toList());

		return filter;
	}

	/**
	 * 
	 * @param pseudo
	 * @return liste des encheres remportees suivant l'utilisateur connecté.
	 */
	public List<DetailsVente> mesEncheresRemportees(String pseudo) {
		List<DetailsVente> filter = null;
		filter = venteEnCours();
		filter = filter.stream().filter(
				(a -> ((pseudo).equals(a.getPseudoAcheteur())) && a.getDateFinEncheres().isBefore(LocalDateTime.now())))
				.collect(Collectors.toList());

		return filter;
	}

	public List<DetailsVente> find(String str) throws DALException {
		List<DetailsVente> filter = new ArrayList<DetailsVente>();
		List<DetailsVente> list = new ArrayList<DetailsVente>();
		
		try {
			filter = dao.bouttonRechercher(str);
			list = filter;
			for (int i = 0; i < filter.size(); i++) {
				for (int j = 0; j < filter.size(); j++) {

					if (filter.get(i).getNoArticle() == filter.get(j).getNoArticle() && j != i) {
						if (filter.get(i).getMontant_enchere() > filter.get(j).getMontant_enchere()) {
							list.remove(j);
						} else {
							list.remove(i);
						}

					}

				}

			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		
		return list;
	}

}
