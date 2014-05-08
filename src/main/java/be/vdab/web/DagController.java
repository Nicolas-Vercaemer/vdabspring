package be.vdab.web;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dagen")
public class DagController {
	private static final String VIEW = "dagen/dag";

	@RequestMapping(value = "{datum}", method = RequestMethod.GET)
	ModelAndView kalender(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datum);
		return new ModelAndView(VIEW, "dagVanDeWeek",
				dagVanDeWeek(calendar.get(Calendar.DAY_OF_WEEK)))
				.addObject("dagVanDeMaand", calendar.get(Calendar.DAY_OF_MONTH))
				.addObject("maand", calendar.get(Calendar.MONTH) + 1)
				.addObject("jaar", calendar.get(Calendar.YEAR));
	}

	private String dagVanDeWeek(int dagInt) {
		return new DateFormatSymbols().getWeekdays()[dagInt];
	}

}
