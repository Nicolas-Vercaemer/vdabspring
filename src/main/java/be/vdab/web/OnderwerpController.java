package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	ModelAndView delete(long id) {
		onderwerpService.delete(onderwerpService.read(id));
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll());
	}
	@RequestMapping(value = "bewerken/{id}", method=RequestMethod.GET)
	ModelAndView bewerken(@PathVariable long id){
		Onderwerp onderwerpVoorBewerken = onderwerpService.read(id);
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll())
		.addObject("onderwerpVoorBewerken", onderwerpVoorBewerken);
	}

	@RequestMapping(value= "opslaan", method = RequestMethod.POST)
	public String opslaan(@Valid Onderwerp onderwerp, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addAttribute("onderwerpen", onderwerpService.findAll());
			redirectAttributes.addAttribute("onderwerpVoorToevoegen", new Onderwerp());
			return VIEW;

		}
	
		onderwerpService.createOrUpdate(onderwerp);;
		return "redirect:/onderwerpen";
	}
	@RequestMapping(value="toevoegen", method = RequestMethod.GET)
	ModelAndView toevoegen(){
		Onderwerp onderwerp = new Onderwerp();
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll())
		.addObject("onderwerpVoorToevoegen", onderwerp);
	}


}
