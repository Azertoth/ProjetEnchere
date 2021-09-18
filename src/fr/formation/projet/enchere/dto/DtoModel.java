package fr.formation.projet.enchere.dto;

public class DtoModel {
	DetailsVente dto;

	public DtoModel() {

	}

	public DtoModel(DetailsVente dto) {
		super();
		this.dto = dto;
	}

	public DetailsVente getDto() {
		return dto;
	}

	public void setDto(DetailsVente dto) {
		this.dto = dto;
	}

}
