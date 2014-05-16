package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Cursist;
import be.vdab.services.CursistService;

@Controller
@ExposesResourceFor(Cursist.class)
@RequestMapping("/cursisten")
class CursistController {
	private static final String TOEVOEGEN_VIEW = "cursist/cursisttoevoegen";
	private static final String ACTIVATIE_VIEW = "cursist/activatie";
	private final CursistService cursistService;

	@Autowired
	CursistController(CursistService cursistService) {
		this.cursistService = cursistService;
	}

	// Cursist toevoegen + redirect
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Cursist cursist, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()
				&& cursistService.isEmaiInGebruik(cursist)) {
			bindingResult.reject("emailAdresIsNietUniek");
			return TOEVOEGEN_VIEW;

		}
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;

		}

		cursistService.create(cursist);
		return "redirect:cursisten/toevoegen";
	}

	// Cursist toevoeg form
	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "cursist", new Cursist());
	}

	// Cursist activeren
	@RequestMapping(value = "{id}/activate", method = RequestMethod.GET)
	ModelAndView activeerd(@PathVariable Long id) {
		Cursist cursist = cursistService.read(id);
		if (cursist == null) {
			return new ModelAndView(ACTIVATIE_VIEW, "resultaat",
					"Cursist niet gevonden.");
		} else if (cursist.isActief() == true) {
			return new ModelAndView(ACTIVATIE_VIEW, "resultaat",
					"Cursist is al activeerd.");
		} else {
			cursist.setActief(true);
			cursistService.update(cursist);
			return new ModelAndView(ACTIVATIE_VIEW, "resultaat",
					"Cursist is activeerd.");
		}
	}
}
