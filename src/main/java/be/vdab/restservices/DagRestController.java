package be.vdab.restservices;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.entities.Dag;
import be.vdab.exceptions.DagGegevensNietGevondenException;
import be.vdab.services.DagService;

@RestController
@ExposesResourceFor(Dag.class)
@RequestMapping("/dagen")
class DagRestController {
	private final DagService dagService;

	private final EntityLinks entityLinks;

	@Autowired
	DagRestController(DagService dagService, EntityLinks entityLinks) {
		this.dagService = dagService;
		this.entityLinks = entityLinks;
	}

	// Dag gegevens
	@RequestMapping(value = "{datum}", method = RequestMethod.GET)
	DagGegevensResource read(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum) {

		Dag dag = dagService.findByDatum(datum);

		if (dag == null || dag.getDagDetails() == null) {
			throw new DagGegevensNietGevondenException();
		} else {
			DagGegevensResource dagGegevensResource = new DagGegevensResource(
					dag.getDagDetails());
			return dagGegevensResource;
		}
	}

	// Lijst met dagen
	@RequestMapping(value = "", method = RequestMethod.GET)
	DagenResource findAll() {
		return new DagenResource(dagService.findall(), entityLinks);
	}

	// DagGegevens niet gevonden exception
	@ExceptionHandler(DagGegevensNietGevondenException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void dagNietGevonden() {
	}
}