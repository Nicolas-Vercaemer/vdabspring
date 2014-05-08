package be.vdab.web;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/kalender")
class KalenderController {
	private static final String VIEW = "kalender/kalender";
	private int rij = 0;

	@RequestMapping(value = "{jaar:[1-2][0-9][0-9][0-9]}/{maand:0[1-9]||[1-9]||1[0-2]}", method = RequestMethod.GET)
	ModelAndView kalender(@PathVariable("jaar") int jaar,
			@PathVariable("maand") int maand) {

		int kalender[][] = createKalender(jaar, maand);
		int maxRij = kalender[rij][0] == 0 ? rij - 1 : rij;
		int vorigeMaand = maand - 1;
		int volgendeMaand = maand + 1;
		int vorigJaar = jaar;
		int volgendJaar = jaar;

		if (vorigeMaand == 0) {
			vorigeMaand = 12;
			vorigJaar--;
		}
		if (volgendeMaand == 13) {
			volgendeMaand = 1;
			volgendJaar++;
		}

		return new ModelAndView(VIEW, "kalender", kalender)
				.addObject("maxRij", maxRij)
				.addObject("maand", welkeMaand(maand))
				.addObject("vorigeMaand", vorigeMaand)
				.addObject("vorigeMaandString", welkeMaand(vorigeMaand))
				.addObject("vorigJaar", vorigJaar)
				.addObject("volgendeMaand", volgendeMaand)
				.addObject("volgendeMaandString", welkeMaand(volgendeMaand))
				.addObject("volgendJaar", volgendJaar);
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView kalender() {
		Calendar huidigetijd = Calendar.getInstance();
		int kalender[][] = createKalender(huidigetijd.get(Calendar.YEAR),
				huidigetijd.get(Calendar.MONTH) + 1);
		int maxRij = kalender[rij][0] == 0 ? rij - 1 : rij;
		int vorigeMaand = huidigetijd.get(Calendar.MONTH);
		int vorigJaar = huidigetijd.get(Calendar.YEAR);
		int volgendeMaand = huidigetijd.get(Calendar.MONTH) + 2;
		int volgendJaar = huidigetijd.get(Calendar.YEAR);

		if (vorigeMaand == 0) {
			vorigeMaand = 12;
			vorigJaar--;
		}
		if (volgendeMaand == 13) {
			volgendeMaand = 1;
			volgendJaar++;
		}
		return new ModelAndView(VIEW, "kalender", kalender)
				.addObject("maxRij", maxRij)
				.addObject("maand",
						welkeMaand(huidigetijd.get(Calendar.MONTH) + 1))
				.addObject("vorigeMaand", vorigeMaand)
				.addObject("vorigeMaandString", welkeMaand(vorigeMaand))
				.addObject("vorigJaar", vorigJaar)
				.addObject("volgendeMaand", volgendeMaand)
				.addObject("volgendeMaandString", welkeMaand(volgendeMaand))
				.addObject("volgendJaar", volgendJaar);
	}

	private int bepaalEersteDagVanMaandInEersteWeek(Calendar calendar) {

		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			return 0;
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		default:
			return 6;
		}
	}

	private int[][] createKalender(int jaar, int maand) {
		rij = 0;
		int[][] kalender = new int[6][7];
		Calendar calendar = Calendar.getInstance();
		if (jaar != 0) {
			calendar.set(Calendar.YEAR, jaar);
		}
		if (maand != 0) {
			calendar.set(Calendar.MONTH, maand - 1);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		maand = calendar.get(Calendar.MONTH);
		int kolom = bepaalEersteDagVanMaandInEersteWeek(calendar);

		do {
			kalender[rij][kolom] = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				rij++;
				kolom = 0;
			} else {
				kolom++;
			}
		} while (calendar.get(Calendar.MONTH) == maand);
		return kalender;
	}

	private String welkeMaand(int maand) {
		switch (maand) {
		case 1:
			return "Januari";
		case 2:
			return "Februari";
		case 3:
			return "Maart";
		case 4:
			return "April";
		case 5:
			return "Mei";
		case 6:
			return "Juni";
		case 7:
			return "Juli";
		case 8:
			return "Augustus";
		case 9:
			return "September";
		case 10:
			return "Oktober";
		case 11:
			return "November";
		default:
			return "December";

		}
	}

}
