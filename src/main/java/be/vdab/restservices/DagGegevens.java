package be.vdab.restservices;

import java.util.ArrayList;
import java.util.List;

import be.vdab.valueobjects.DagDetail;

public class DagGegevens {

	DagGegevens() {
	}

	private List<DagGegevensCursist> cursist = new ArrayList<>();

	private List<DagGegevensOnderwerp> onderwerp = new ArrayList<>();

	DagGegevens(DagDetail dagDetail) {
		this.cursist.add(new DagGegevensCursist(dagDetail));
		this.onderwerp.add(new DagGegevensOnderwerp(dagDetail));
	}

	public List<DagGegevensOnderwerp> getOnderwerp() {
		return onderwerp;
	}

	public void setOnderwerp(List<DagGegevensOnderwerp> onderwerp) {
		this.onderwerp = onderwerp;
	}

	public List<DagGegevensCursist> getCursist() {
		return cursist;
	}

	public void setCursist(List<DagGegevensCursist> cursist) {
		this.cursist = cursist;
	}
}