package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Cursist;
import be.vdab.entities.Onderwerp;

@Embeddable
public class DagDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "cursistID")
	private Cursist cursist;

	@ManyToOne
	@JoinColumn(name = "onderwerpID")
	private Onderwerp onderwerp;

	DagDetail() {
	}

	public DagDetail(Cursist cursist, Onderwerp onderwerp) {
		this.cursist = cursist;
		this.onderwerp = onderwerp;
	}

	public Cursist getCursist() {
		return cursist;
	}

	public Onderwerp getOnderwerp() {
		return onderwerp;
	}

	public void setCursist(Cursist cursist) {
		this.cursist = cursist;
	}

	public void setOnderwerp(Onderwerp onderwerp) {
		this.onderwerp = onderwerp;
	}

	@Override
	public String toString() {
		return "DagDetail [cursist=" + cursist + ", onderwerp=" + onderwerp
				+ "]";
	}
}