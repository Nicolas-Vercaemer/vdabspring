package be.vdab.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Dag;
import be.vdab.services.CursistService;
import be.vdab.services.DagService;
import be.vdab.services.OnderwerpService;
import be.vdab.valueobjects.DagDetail;

@Controller
@RequestMapping(value = "/dagen", produces = MediaType.TEXT_HTML_VALUE)
public class DagController {
	private static final String VIEW = "dagen/dag";
	private final DagService dagService;
	private final OnderwerpService onderwerpService;
	private final CursistService cursistService;

	@Autowired
	public DagController(DagService dagService,
			OnderwerpService onderwerpService, CursistService cursistService) {
		this.dagService = dagService;
		this.onderwerpService = onderwerpService;
		this.cursistService = cursistService;
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.GET)
	ModelAndView kalender(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			DagDetail dagDetail) {

		Iterable<Dag> test = dagService.findAll();
		for (Dag dag : dagService.findAll()) {
			System.out.println(dag.getDatum());
			for (DagDetail dagDetail2 : dag.getDagDetails()) {
				System.out.println(dagDetail2.toString());
			}
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);
		return new ModelAndView(VIEW, "huidigeDatum", datum)
				.addObject("vorigeDagDatum", vorigeDagBerekenen(datum))
				.addObject("volgendeDagDatum", volgendeDagBerekenen(datum))
				.addObject("dagGegevens", dagService.findByDatum(datum));
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.POST, params = "toevoegForm")
	ModelAndView toevoegForm(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			DagDetail dagDetail, BindingResult bindingResult) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);
		return kalender(datum, dagDetail).addObject("onderwerpen",
				onderwerpService.findAll()).addObject("cursisten",
				cursistService.findAll());
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.POST)
	String toevoegen(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			DagDetail dagDetail, BindingResult bindingResult) {
		Dag dag = dagService.findByDatum(datum);

		if (dag == null) {
			dag = new Dag(datum, new TreeSet<DagDetail>());
		}

		dag.getDagDetails().add(dagDetail);
		dagService.save(dag);

		return "redirect:/dagen/"
				+ new SimpleDateFormat("yyyy-MM-dd").format(datum);
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.DELETE)
	String verwijderen(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			@RequestParam("verwijderen") Long id) {
		Dag dag = dagService.findByDatum(datum);

		for (Iterator<DagDetail> it = dag.getDagDetails().iterator(); it
				.hasNext();) {
			DagDetail entry = it.next();
			if (entry.getId() == id) {
				it.remove();
				break;
			}
		}

		dagService.save(dag);

		return "redirect:/dagen/"
				+ new SimpleDateFormat("yyyy-MM-dd").format(datum);
	}

	private Calendar vorigeDagBerekenen(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.DAY_OF_WEEK,
				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ? -3 : -1);

		return calendar;
	}

	private Calendar volgendeDagBerekenen(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.DAY_OF_WEEK,
				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ? 3 : 1);

		return calendar;
	}
}