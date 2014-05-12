package be.vdab.web;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Dag;
import be.vdab.services.CursistService;
import be.vdab.services.DagService;
import be.vdab.services.OnderwerpService;
import be.vdab.valueobjects.DagDetail;

@Controller
@RequestMapping("/dagen")
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
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);

		return new ModelAndView(VIEW, "huidigeDag",
				dagVanDeWeek(calendar.get(Calendar.DAY_OF_WEEK)))
				.addObject("huidigeDatum", datum)
				.addObject(
						"vorigeDag",
						dagVanDeWeek(vorigeDagBerekenen(datum).get(
								Calendar.DAY_OF_WEEK)))
				.addObject("vorigeDagDatum", vorigeDagBerekenen(datum))
				.addObject(
						"volgendeDag",
						dagVanDeWeek(volgendeDagBerekenen(datum).get(
								Calendar.DAY_OF_WEEK)))
				.addObject("volgendeDagDatum", volgendeDagBerekenen(datum))
				.addObject("dagGegevens", dagService.findByDatum(datum))
				.addObject("onderwerpen", onderwerpService.findAll())
				.addObject("cursisten", cursistService.findAll());
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.POST, params = "toevoegForm")
	ModelAndView toevoegForm(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			DagDetail dagDetail, BindingResult bindingResult) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);
		return kalender(datum);
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.POST)
	String toevoegen(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum,
			DagDetail dagDetail, BindingResult bindingResult) {

		Dag dag = dagService.findByDatum(datum);

		if (dag == null) {
			dag = new Dag(datum, new HashSet<DagDetail>());
		}

		dag.getDagDetails().add(dagDetail);
		dagService.save(dag);

		return "redirect:/dagen/"
				+ new SimpleDateFormat("yyyy-MM-dd").format(datum);
	}

	@RequestMapping(value = "{datum}", method = RequestMethod.POST, params = "verwijderen")
	String verwijderen(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum) {

		/*
		 * Dag dag = dagService.findByDatum(datum);
		 * 
		 * System.out.println(dagDetail.toString());
		 */

		/*
		 * for(Iterator<DagDetail> it = dag.getDagDetails().iterator();
		 * it.hasNext();){ DagDetail entry = it.next();
		 * if(entry.getCursist().equals("dagDe")) { it.remove(); }
		 */

		// dagService.save(dag);

		// dagService.delete(dagDetail);
		return "redirect:/dagen/"
				+ new SimpleDateFormat("yyyy-MM-dd").format(datum);
	}

	private Calendar vorigeDagBerekenen(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			calendar.add(Calendar.DAY_OF_WEEK, -3);
		} else {
			calendar.add(Calendar.DAY_OF_WEEK, -1);
		}
		return calendar;
	}

	private Calendar volgendeDagBerekenen(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			calendar.add(Calendar.DAY_OF_WEEK, +3);
		} else {
			calendar.add(Calendar.DAY_OF_WEEK, +1);
		}
		return calendar;
	}

	private String dagVanDeWeek(int dagInt) {
		return new DateFormatSymbols().getWeekdays()[dagInt];
	}

}
