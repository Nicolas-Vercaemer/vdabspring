package be.vdab.restservices;

import be.vdab.valueobjects.DagDetail;

public class DagGegevensOnderwerp {

	private String onderwerp;

	DagGegevensOnderwerp() {
	}

	DagGegevensOnderwerp(DagDetail dagDetail) {
		this.onderwerp = dagDetail.getOnderwerp().getNaam();
	}

	public String getOnderwerp() {
		return onderwerp;
	}

	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

}