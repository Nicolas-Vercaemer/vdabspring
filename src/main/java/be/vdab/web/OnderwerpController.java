package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Onderwerp;
import be.vdab.services.OnderwerpService;

@Controller
@RequestMapping("/onderwerpen")
public class OnderwerpController {
	private static final String VIEW = "onderwerpen/onderwerpen";
	private final OnderwerpService onderwerpService;

	@Autowired
	public OnderwerpController(OnderwerpService onderwerpService) {
		this.onderwerpService = onderwerpService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll());
	}

	@RequestMapping(value = "verwijderen", method = RequestMethod.POST)
	ModelAndView delete(Onderwerp onderwerp) {
		try {
			onderwerpService.delete(onderwerp);
		} catch (DataIntegrityViolationException ex) {
			return findAll().addObject("foutverwijderen",
					"Kan onderwerp niet verwijderen het is in gebruik.");
		}
		return new ModelAndView("redirect:/onderwerpen");
	}

	@RequestMapping(value = "bewerken/{onderwerp}", method = RequestMethod.GET)
	ModelAndView bewerken(@PathVariable Onderwerp onderwerp) {
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll())
				.addObject("onderwerp", onderwerp);
	}

	@RequestMapping(value = "opslaan", method = RequestMethod.POST)
	public ModelAndView opslaan(@Valid Onderwerp onderwerp,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (onderwerpService.isNaamAlInGebruik(onderwerp)) {
				bindingResult.reject("naamIsNietUniek");
			}
		}

		if (bindingResult.hasErrors()) {
			return new ModelAndView(VIEW, "onderwerpen",
					onderwerpService.findAll());
		}
		onderwerpService.createOrUpdate(onderwerp);
		return new ModelAndView("redirect:/onderwerpen");
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView toevoegen() {
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll())
				.addObject("onderwerp", new Onderwerp());
	}

}
