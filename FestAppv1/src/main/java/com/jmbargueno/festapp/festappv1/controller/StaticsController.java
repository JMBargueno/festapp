/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;

/**
 * 
 * Clase con los contrladores de las vista "básicas"
 * 
 * @author jmbargueno
 *
 */
@Controller
public class StaticsController {

	@Autowired
	PartyTypeService partyTypeService;

	/**
	 * Controlador que lleva al indice de la aplicacion
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/index", "/" })
	public String index(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/index.html";
	}

	/**
	 * 
	 * Pantalla de evento realizado con exito
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/success", })
	public String success(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/success.html";
	}

	/**
	 * Controlador que lleva a las vista para contactar
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/contacto", "/contact" })
	public String contacto(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/contact.html";
	}

	/**
	 * Controlador para mostrar aviso legal de la web
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/legal")
	public String legal(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "common/legal.html";
	}

	@GetMapping("/images/null")
	public String imageNull(Model model) {
		return "redirect:/index";
	}

	/**
	 * Controlador que te lleva a la página de cada tipo de fiesta
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
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
