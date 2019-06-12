package com.jmbargueno.festapp.festappv1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.PurchaseLine;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.ProductService;
import com.jmbargueno.festapp.festappv1.service.PurchaseLineService;
import com.jmbargueno.festapp.festappv1.service.PurchaseService;
import com.jmbargueno.festapp.festappv1.service.ShoppingCartService;
import com.jmbargueno.festapp.festappv1.service.UserService;

/**
 * Clase controladora del carrito
 * 
 * @author jmbargueno
 *
 */
@Controller
public class ShoppingCartController {
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	@Autowired
	UserService userService;

	@Autowired
	PartyTypeService partyTypeService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private PurchaseLineService purchaseLineService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService, PurchaseLineService purchaseLineService) {
		this.shoppingCartService = shoppingCartService;
		this.purchaseLineService = purchaseLineService;
	}

	/**
	 * Controlador que muestra el carrito
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/cart")
	public String showCarrito(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		if (model.addAttribute("products", shoppingCartService.getProductsInCart()) == null) {
			return "redirect:/index";
		}

		return "common/cart.html";
	}

	/**
	 * Controlador que añade un producto al carrito
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/cart/add/{id}")
	public String productoACarrito(@PathVariable("id") long id, Model model) {

		shoppingCartService.addPurchaseLine((productService.findById(id)));

		return "redirect:/products/list";
	}

	/**
	 * Controlador que elimina un producto al carrito
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/cart/remove/{id}")
	public String removeProductFromCart(@PathVariable("id") long id) {
		
		List<PurchaseLine> list = shoppingCartService.getProductsInCart();
		
		PurchaseLine removedLine = null;
		
		for (PurchaseLine purchaseLine : list) {
			
			if(purchaseLine.getT().getId() == id) {
				removedLine = purchaseLine;
			}
		}
		shoppingCartService.removePurchaseLine(removedLine);
		
		return "redirect:/cart";
	}

	/**
	 * Controlador total carrito
	 * 
	 * @return
	 */
	@ModelAttribute("cartTotal")
	public Double totalCart() {

		List<PurchaseLine> carrito = shoppingCartService.getProductsInCart();
		double total = 0.0;
		if (carrito != null) {
			for (PurchaseLine p : carrito) {
				total += p.getT().getPrice() * p.getQuantity();
			}
			return total;
		}

		return 0.0;
	}

	/**
	 * Controlador que guarda y realice el checkout de la compra
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/cart/checkout")
	public String showCompra(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();
		UserFA usuario = userService.searchByUsername(user.getUsername());

		List<PurchaseLine> productInCart = shoppingCartService.getProductsInCart();
		Purchase purchase = new Purchase();

		for (PurchaseLine purchaseLine : productInCart) {
			purchaseLine.setPurchase(purchase);
			productService.modifyStock(purchaseLine.getT().getId(), purchaseLine.getQuantity());

		}
		
		purchase.setDate(LocalDate.now());
		purchase.setFinalPrice(shoppingCartService.calcFinalPrice());
		purchase.setPurchaseList(productInCart);
		purchase.setUserFA(usuario);
		purchase.setThisOwnerUserName();

		model.addAttribute("purchase", purchaseService.save(purchase));
		shoppingCartService.resetCart();

		return "common/checkout.html";
	}

}
