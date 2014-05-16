package be.vdab.valueobjects;

import java.util.Date;

public class CursistenDag {
	private Date datum;
	private long id;

	public CursistenDag(Date datum, long id) {
		this.datum = datum;
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CursistenDag [datum=" + datum + ", aantalCursisten=" + id + "]";
	}

}
