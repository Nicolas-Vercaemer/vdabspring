package be.vdab.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import be.vdab.entities.Dag;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "Dagen")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class DagenResource extends ResourceSupport {

	@XmlElement(name = "dag")
	@JsonProperty("dag")
	private List<DagDatum> dagDatum = new ArrayList<>();

	DagenResource() {
	}

	DagenResource(Iterable<Dag> dagen, EntityLinks entityLinks) {
		for (Dag dag : dagen) {
			this.dagDatum.add(new DagDatum(dag));
			this.add(entityLinks
					.linkToSingleResource(Dag.class, dag.getDatum()).withRel(
							dag.getDatum().toString()));
		}
		this.add(entityLinks.linkToCollectionResource(Dag.class));
	}
}