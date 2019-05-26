/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;

/**
 * @author jmbargueno
 *
 */
@Controller
@RequestMapping("/party")
public class PartyTypeController {

	@Autowired
	PartyTypeService partyTypeService;

	@GetMapping("/{id}")
	public String showPartyaTypeID(@PathVariable("id") long id, Model model) {

		model.addAttribute("partiesList", partyTypeService.findAll());
		PartyType showedParty = partyTypeService.findById(id);

		if (showedParty != null) {
			model.addAttribute("party", showedParty);
			return "common/parties.html";
		} else {
			
			return "redirect:/";
		}
	}

}
