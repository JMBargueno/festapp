/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.PurchaseLine;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.PurchaseLineService;
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

	@Autowired
	PurchaseLineService purchaseLineService;

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

	@GetMapping("/profile/history")
	public String showPurchases(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("id") Optional<Long> id,
			@RequestParam("dateFilter") Optional<Integer> dateFilter, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();

		UserFA loggedUser = userService.searchByUsername(user.getUsername());
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Purchase> purchases = null;

		Integer evalDate = dateFilter.orElse(null);

		if (evalDate == null) {
			purchases = purchaseService.findByUserFA(loggedUser, PageRequest.of(evalPage, evalPageSize));
		} else {

			switch (dateFilter.get()) {
			case 0:
				purchases = purchaseService.findByUserFA(loggedUser, PageRequest.of(evalPage, evalPageSize));

				break;

			case 1:
				purchases = purchaseService.findByUserAndMonth(loggedUser, PageRequest.of(evalPage, evalPageSize));
				break;

			case 2:
				purchases = purchaseService.findByUserAndYear(loggedUser, PageRequest.of(evalPage, evalPageSize));
				break;

			default:
				purchases = purchaseService.findByUserFA(loggedUser, PageRequest.of(evalPage, evalPageSize));
				break;
			}
		}

		Pager pager = new Pager(purchases.getTotalPages(), purchases.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("purchases", purchases);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "common/oneUserHistoric.html";
	}

	/**
	 * Método que calcula gastos totales
	 * 
	 * @param dateFilter
	 * @return
	 */
	@ModelAttribute("cartTotalUser")
	public Double totalPurchasesForUser(Optional<Integer> dateFilter) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof AnonymousAuthenticationToken) {
			return 0.0;
		} else {

			User user = (User) auth.getPrincipal();

			UserFA loggedUser = userService.searchByUsername(user.getUsername());

			double total = 0;

			if (loggedUser != null) {
				total = purchaseService.calcAllPurchases(loggedUser, dateFilter);
			}
			return total;
		}
	}

	@GetMapping("/profile/history/details/{id}")
	public String showPurchasesOneHistoricDetails(@PathVariable("id") long id,
			@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page,
			Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Purchase searchedPurchase = purchaseService.findById(id);
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<PurchaseLine> purchaseLines = null;

		purchaseLines = purchaseLineService.findByPurchase(searchedPurchase, PageRequest.of(evalPage, evalPageSize));

		Pager pager = new Pager(purchaseLines.getTotalPages(), purchaseLines.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("purchaseLines", purchaseLines);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "common/userhistoricdetails.html";
	}

}
