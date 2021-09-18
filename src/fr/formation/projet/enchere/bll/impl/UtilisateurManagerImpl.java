package fr.formation.projet.enchere.bll.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.formation.projet.enchere.bll.UtilisateurManager;
import fr.formation.projet.enchere.bll.exception.BLLException;
import fr.formation.projet.enchere.bo.Utilisateur;
import fr.formation.projet.enchere.dal.DAOFact;
import fr.formation.projet.enchere.dal.UtilisateurDAO;
import fr.formation.projet.enchere.dal.exception.DALException;
import fr.formation.projet.enchere.ihm.modeluser.UtilisateurModel;

public class UtilisateurManagerImpl implements UtilisateurManager {
	UtilisateurDAO dao = DAOFact.getUtilisateurDAO();
	List<Utilisateur> lstUtilisateur = new ArrayList<Utilisateur>();
	BLLException exception = new BLLException();

	@Override
	public boolean insert(Utilisateur utilisateur) throws BLLException {
		boolean estInsere = false;
		try {
			if (isAlphanumeric(utilisateur.getPseudo())) {
				System.out.println("isAlphanumeric OK");
				if (isUnique(utilisateur, utilisateur.getEmail())) {
					System.out.println("isUniqueMail OK");
					if (isUnique(utilisateur, utilisateur.getPseudo())) {
						System.out.println("isUniquePseudo OK");
						if (utilisateur.getConfirmation().equals(utilisateur.getMotDePasse())) {
							System.out.println("confirm OK");
							if (isAMail(utilisateur.getEmail())) {
								System.out.println("isAMail OK");
								dao.insert(utilisateur);
								estInsere = true;
							} else {
								exception.ajoutMessage("Ce n'est pas un mail");
								System.out.println("Ce n'est pas un mail");
							}

						} else {
							System.out.println("Confirm NOK");
							exception.ajoutMessage("Les deux mots de passe sont différents");
						}
					} else {
						System.out.println("isUniquePseudo NOK");
						exception.ajoutMessage("Ce pseudo existe déjà");
					}
				} else {
					System.out.println("isUniqueMail NOK");
					exception.ajoutMessage("Ce mail existe déjà");

				}
			} else {
				System.out.println("isAlphanumeric NOK");
				exception.ajoutMessage("Le pseudo doit être un Alphanuméric");
			}

		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("(INSERT UtilisateurManager)Problème lié à l'insertion en Base de donnée");

		}
		return estInsere;
	}

	@Override
	public List<Utilisateur> select() throws BLLException {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		try {

			list = dao.selectAll();

		} catch (DALException e) {
			exception.ajoutMessage("La selection n'a pas fonctionné");
		}
		return list;
	}

	@Override
	public boolean update(Utilisateur utilisateur, UtilisateurModel model) throws BLLException {

		boolean estUpdate = false;
		try {
			if (isAlphanumeric(utilisateur.getPseudo())) {
				System.out.println("isAlphanumeric OK");
				if (isUnique(utilisateur, utilisateur.getEmail())
						|| utilisateur.getEmail().equals(model.getUser().getEmail())) {
					System.out.println("isUniqueMail OK");
					if (isUnique(utilisateur, utilisateur.getPseudo())
							|| utilisateur.getPseudo().equals(model.getUser().getPseudo())) {
						System.out.println("isUniquePseudo OK");
						if (utilisateur.getConfirmation().equals(utilisateur.getMotDePasse())) {
							System.out.println("confirm OK");
							if (isAMail(utilisateur.getEmail())) {
								System.out.println("isAMail OK");
								if ("".equals(utilisateur.getMotDePasse())) {
									utilisateur.setMotDePasse(model.getUser().getMotDePasse());
								}
								dao.update(utilisateur);

								estUpdate = true;
							} else {
								System.out.println("C'est pas un mail boulet");
								exception.ajoutMessage("Ce n'est pas un mail");
							}

						} else {
							System.out.println("Confirm NOK");
							exception.ajoutMessage("Les deux mots de passe sont différents");
						}
					} else {
						System.out.println("isUniquePseudo NOK");
						exception.ajoutMessage("Ce pseudo existe déjà");
					}
				} else {
					System.out.println("isUniqueMail NOK");

					exception.ajoutMessage("Ce n'est pas un mail");

				}
			} else {
				System.out.println("isAlphanumeric NOK");
				exception.ajoutMessage("Le pseudo doit être un Alphanuméric");
			}
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("(UPDATE UtilisateurManager)Problème lié à l'insertion en Base de donnée");

		}
		return estUpdate;

	}

	@Override
	public void delete(Utilisateur utilisateur) throws BLLException {
		try {
			dao.delete(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage("La suppression a échoué" + e.getMessage());

		}

	}

	/*
	 * Test si Alphanumeric. Converti une chaîne de 'char' en un tableau de char.
	 * Parcours chaque 'char' et check si c'est un Alphanumeric ou non, renvoi un
	 * boolean
	 */

	public boolean isAlphanumeric(String str) {

		String regex = "^[a-zA-Z0-9]+$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	public boolean isUnique(Utilisateur utilisateur, String string) throws BLLException {
		boolean isUnique = true;
		try {
			lstUtilisateur = dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();

			exception.ajoutMessage("Liste non récupérée" + e.getMessage());

		}

		for (Utilisateur item : lstUtilisateur) {
			if (isAMail(string)) {
				if (item.getEmail().equals(utilisateur.getEmail())) {
					isUnique = false;
				} else {
				}
			} else {
				if (item.getPseudo().equals(utilisateur.getPseudo())) {
					isUnique = false;
				} else {
				}
			}
		}

		return isUnique;
	}

	@Override
	public boolean isAMail(String str) {

		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(str);
		return matcher.matches();
	}

	@Override
	public boolean update(Utilisateur t) throws BLLException {
		boolean flag = false;
		try {
			dao.update(t);
			flag = true;
		} catch (DALException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Utilisateur selectByPseudo(String str) throws BLLException {
		Utilisateur user = null;
		try {
			user = dao.selectByPseudo(str);
		} catch (DALException e) {
			e.printStackTrace();
			exception.ajoutMessage(e.getMessage());

		}
		return user;
	}

	@Override
	public void updateCredit(Utilisateur user, Integer credit) throws BLLException {
		try {
			dao.updateCredit(user, credit);
		} catch (DALException e) {
			exception.ajoutMessage(e.getMessage());
		}

	}

}
