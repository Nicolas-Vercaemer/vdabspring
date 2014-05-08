package be.vdab.web;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
	private static final Map<Integer, Integer> eersteMaandDagKolom = new HashMap<>();

	static {
		eersteMaandDagKolom.put(Calendar.MONDAY, 0);
		eersteMaandDagKolom.put(Calendar.TUESDAY, 1);
		eersteMaandDagKolom.put(Calendar.WEDNESDAY, 2);
		eersteMaandDagKolom.put(Calendar.THURSDAY, 3);
		eersteMaandDagKolom.put(Calendar.FRIDAY, 4);
		eersteMaandDagKolom.put(Calendar.SATURDAY, 5);
		eersteMaandDagKolom.put(Calendar.SUNDAY, 6);
	}

	@RequestMapping(value = "{jaar:[1-2][0-9][0-9][0-9]}/{maand:0[1-9]||[1-9]||1[0-2]}", method = RequestMethod.GET)
	ModelAndView kalender(@PathVariable("jaar") int jaar,
			@PathVariable("maand") int maand) {

		Calendar calendar = Calendar.getInstance();
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
				.addObject("maandString", welkeMaand(maand))
				.addObject("jaar", jaar).addObject("maand", maand)
				.addObject("vorigeMaand", vorigeMaand)
				.addObject("vorigeMaandString", welkeMaand(vorigeMaand))
				.addObject("vorigJaar", vorigJaar)
				.addObject("volgendeMaand", volgendeMaand)
				.addObject("volgendeMaandString", welkeMaand(volgendeMaand))
				.addObject("volgendJaar", volgendJaar)
				.addObject("huidigeDag", calendar.get(Calendar.DAY_OF_MONTH))
				.addObject("huidigeMaand", calendar.get(Calendar.MONTH) + 1)
				.addObject("huidigJaar", calendar.get(Calendar.YEAR));
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView kalender() {
		Calendar huidigetijd = Calendar.getInstance();
		return kalender(huidigetijd.get(Calendar.YEAR),
				huidigetijd.get(Calendar.MONTH) + 1);
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
		int kolom = eersteMaandDagKolom.get(calendar.get(Calendar.DAY_OF_WEEK));

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
		return new DateFormatSymbols().getMonths()[maand - 1];
	}

}
