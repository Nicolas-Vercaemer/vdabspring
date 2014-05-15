package be.vdab.restservices;

import be.vdab.valueobjects.DagDetail;

public class DagGegevensCursist {

	private String voornaam;
	private String familienaam;

	DagGegevensCursist() {
	}

	DagGegevensCursist(DagDetail dagDetail) {
		this.voornaam = dagDetail.getCursist().getVoornaam();
		this.familienaam = dagDetail.getCursist().getFamilienaam();
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
}