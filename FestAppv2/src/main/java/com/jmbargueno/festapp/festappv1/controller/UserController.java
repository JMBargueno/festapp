/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.PurchaseService;
import com.jmbargueno.festapp.festappv1.service.UserService;

/**
 * Clase con los controladores de usuario
 * 
 * @author jmbargueno
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	PartyTypeService partyTypeService;

	@Autowired
	PurchaseService purchaseService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

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
	 * 
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

	@GetMapping("/profile/history")
	public String showPurchases(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("id") Optional<Long> id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();

		UserFA loggedUser = userService.searchByUsername(user.getUsername());
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Purchase> purchases = null;
		purchases = purchaseService.findByUserFA(loggedUser, PageRequest.of(evalPage, evalPageSize));

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		// Page<Producto> products =
		// productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(purchases.getTotalPages(), purchases.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("purchases", purchases);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "common/userhistoric.html";
	}

}
