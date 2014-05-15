package be.vdab.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.valueobjects.DagDetail;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "activiteiten")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class DagGegevensResource {

	@XmlElement(name = "activiteit")
	@JsonProperty("activiteit")
	private List<DagGegevens> dagGegevens = new ArrayList<>();

	DagGegevensResource() {
	}

	DagGegevensResource(Iterable<DagDetail> dagDetails) {
		for (DagDetail dagDetail : dagDetails) {
			this.dagGegevens.add(new DagGegevens(dagDetail));
		}
	}

}
