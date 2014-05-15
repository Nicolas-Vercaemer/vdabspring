package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.TemperatuurService;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	private final TemperatuurService temperatuurService;

	@Autowired
	public IndexController(TemperatuurService temperatuurService) {
		this.temperatuurService = temperatuurService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		return new ModelAndView(VIEW).addObject("temperatuurGegevens",
				temperatuurService.temperatuur());
	}
}