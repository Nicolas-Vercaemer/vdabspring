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

	// private long id;
	// private long dagID;

	@ManyToOne
	@JoinColumn(name = "cursistID")
	private Cursist cursist;

	@ManyToOne
	@JoinColumn(name = "onderwerpID")
	private Onderwerp onderwerp;

	/*
	 * public long getId() { return id; }
	 * 
	 * public long getDagID() { return dagID; }
	 */
	public Cursist getCursist() {
		return cursist;
	}

	public Onderwerp getOnderwerp() {
		return onderwerp;
	}

}
