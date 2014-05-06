package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", false);}
	
	
	@RequestMapping(value = "verwijderen", method = RequestMethod.POST)
	ModelAndView delete(long id) {
		onderwerpService.delete(onderwerpService.read(id));
		return new ModelAndView("redirect:/onderwerpen");
	}
	@RequestMapping(value = "bewerken/{id}", method=RequestMethod.GET)
	ModelAndView bewerken(@PathVariable long id){
		Onderwerp onderwerp = onderwerpService.read(id);
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll())
		.addObject("onderwerp", onderwerp);
	}

	@RequestMapping(value= "opslaan", method = RequestMethod.POST)
	public ModelAndView opslaan(@Valid Onderwerp onderwerp, BindingResult bindingResult) {
	

		if (bindingResult.hasErrors()) {
			if(onderwerp.getId()!=0){
		

				return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", true);
				
			}
			else{
				return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", false);
			}
			

	
		}
		if(onderwerp.getId()!=0){
			if(!onderwerp.getNaam().equalsIgnoreCase( onderwerpService.read(onderwerp.getId()).getNaam())){
				if(onderwerpService.isNaamAlInGebruik(onderwerp)){
					bindingResult.reject("naamIsNietUniek");
					return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", true);
				}
		}
		}
		else{
			if(onderwerpService.isNaamAlInGebruik(onderwerp)){
				bindingResult.reject("naamIsNietUniek");
				return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", false);
			}
		}

	
		onderwerpService.createOrUpdate(onderwerp);;
		return new ModelAndView("redirect:/onderwerpen");
	}
	@RequestMapping(value="toevoegen", method = RequestMethod.GET)
	ModelAndView toevoegen(){
		Onderwerp onderwerp = new Onderwerp();
		return new ModelAndView(VIEW, "onderwerpen", onderwerpService.findAll()).addObject("bewerken", false)
		.addObject("onderwerp", onderwerp);
	}


}
