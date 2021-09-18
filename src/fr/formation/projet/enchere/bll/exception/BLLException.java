package fr.formation.projet.enchere.bll.exception;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {

	private static final long serialVersionUID = 1L;

	List<String> messages;

	public BLLException() {
		messages = new ArrayList<String>();
	}

	public void ajoutMessage(String msg) {
		messages.add(msg);
	}

	public List<String> getMessages() {
		return messages;
	}

	public boolean estVide() {
		return messages.isEmpty();
	}
}
