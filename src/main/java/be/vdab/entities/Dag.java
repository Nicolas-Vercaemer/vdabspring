package be.vdab.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.valueobjects.DagDetail;

@Entity
@Table(name = "dagen")
public class Dag {

	@Id
	@GeneratedValue
	private long id;
	@Temporal(TemporalType.DATE)
	private Date datum;

	@ElementCollection
	@CollectionTable(name = "dagdetails", joinColumns = @JoinColumn(name = "dagid"))
	private Set<DagDetail> dagDetails;

	Dag() {
	}

	public long getId() {
		return id;
	}

	public Date getDatum() {
		return datum;
	}

	public Set<DagDetail> getDagDetails() {
		return dagDetails;
	}
}