package be.vdab.web;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.DagService;

@Controller
@RequestMapping("/dagen")
public class DagController {
	private static final String VIEW = "dagen/dag";
	private final DagService dagService;

	@Autowired
	public DagController(DagService dagService) {
		this.dagService = dagService;
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
				.addObject("dagGegevens", dagService.findByDatum(datum));
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
