package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// enkele imports ...
@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
	return new ModelAndView(VIEW); 
	}

}