/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;


/**
 * @author jmbargueno
 *
 */
@Controller
public class StaticsController {
	
	@Autowired
	PartyTypeService partyTypeService;

	@GetMapping({"/index", "/"})
	public String index(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/index.html";
	}
	@GetMapping({"/success",})
	public String success(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/success.html";
	}
	@GetMapping({"/contacto", "/contact"})
	public String contacto(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/contact.html";
	}

	@GetMapping("/legal")
	public String legal(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/legal.html";
	}

	
	@GetMapping("/party/{id}")
	public String showParty(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		PartyType showedParty = partyTypeService.findById(id);

		if (showedParty != null) {
			model.addAttribute("showedParty", showedParty);
			return "common/party";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/";
		}
	}


}
