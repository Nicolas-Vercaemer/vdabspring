package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Cursist;
import be.vdab.services.CursistService;

@Controller
@RequestMapping("/cursisten")
class CursistController {
	private static final String TOEVOEGEN_VIEW = "cursisttoevoegen";
	private final CursistService cursistService;

	@Autowired
	CursistController(CursistService cursistService) {
		this.cursistService = cursistService;
	}

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

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "cursist", new Cursist());
	}

}
