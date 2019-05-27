/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.UserService;

/**
 * Clase con los controladores de usuario
 * @author jmbargueno
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	PartyTypeService partyTypeService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * Controller que lleva a la panta de registro de usuario
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/registro")
	public String signUp(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("userform", new UserFA());
		return "common/register.html";
	}

	/**
	 * Submit del registro del usuario
	 * @param userFA
	 * @param model
	 * @return
	 */
	@PostMapping("/registro/submit")
	public String registroSubmit(@ModelAttribute("userform") UserFA userFA, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userFA.setPassword(passwordEncoder.encode(userFA.getPassword()));
		userService.save(userFA);

		return "redirect:/";
	}
	
//Para la próxima version.
	
//	@GetMapping("/profile/history")
//	public String showPurchases(@RequestParam("pageSize") Optional<Integer> pageSize,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("id") Optional<Long> id, Model model) {
//		model.addAttribute("partiesList", partyTypeService.findAll());
//		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
//		// el tamaño de página inicial.
//		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//
//		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
//		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
//		// del parámetro decrementado en 1.
//		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//
//		Long evalNombre = id.orElse(null);
//
//		Page<Purchase> purchases = null;
//
//		if (evalNombre == null) {
//			purchases = purchaseService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
//		} else {
//			purchases = purchaseService.findById(evalNombre, PageRequest.of(evalPage, evalPageSize));
//		}
//
//		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
//		// nuestro modelo
//		// Page<Producto> products =
//		// productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
//		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
//		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
//		// botones
//		// debe mostrar y cuál es el número de objetos a dibujar.
//		Pager pager = new Pager(purchases.getTotalPages(), purchases.getNumber(), BUTTONS_TO_SHOW);
//
//		model.addAttribute("purchases", purchases);
//		model.addAttribute("selectedPageSize", evalPageSize);
//		model.addAttribute("pageSizes", PAGE_SIZES);
//		model.addAttribute("pager", pager);
//
//		return "admin/tables/purchases.html";
//	}


}
