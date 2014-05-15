package be.vdab.restservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import be.vdab.entities.Dag;

//@XmlAccessorType(XmlAccessType.FIELD)
//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class DagDatum {
	// @XmlAttribute
	private String datum;

	DagDatum() {
	}

	DagDatum(Dag dag) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.datum = df.format(dag.getDatum());
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

}