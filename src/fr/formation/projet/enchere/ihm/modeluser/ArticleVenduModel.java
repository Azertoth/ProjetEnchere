package fr.formation.projet.enchere.ihm.modeluser;

import java.util.ArrayList;
import java.util.List;

import fr.formation.projet.enchere.dto.DetailsVente;

public class ArticleVenduModel {

	private DetailsVente dto;
	private List<DetailsVente> lstDto = new ArrayList<>();

	public ArticleVenduModel() {
		// TODO Auto-generated constructor stub
	}

	public ArticleVenduModel(DetailsVente dto) {
		super();
		this.dto = dto;
	}

	public DetailsVente getDto() {
		return dto;
	}

	public void setDto(DetailsVente dto) {
		this.dto = dto;
	}

	public List<DetailsVente> getLstDto() {
		return lstDto;
	}

	public void setLstDto(List<DetailsVente> lstDto) {
		this.lstDto = lstDto;
	}

	@Override
	public String toString() {
		return "ArticleVenduModel [dto=" + dto + "]";
	}

}
