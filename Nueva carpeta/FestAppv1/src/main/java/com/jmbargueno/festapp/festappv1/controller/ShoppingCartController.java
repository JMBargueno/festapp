package com.jmbargueno.festapp.festappv1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmbargueno.festapp.festappv1.model.PurchaseLine;
import com.jmbargueno.festapp.festappv1.service.ProductService;
import com.jmbargueno.festapp.festappv1.service.PurchaseLineService;
import com.jmbargueno.festapp.festappv1.service.PurchaseService;
import com.jmbargueno.festapp.festappv1.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

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

	@GetMapping("/cart")
	public String showCarrito(Model model) {

		if (model.addAttribute("products", shoppingCartService.getProductsInCart()) == null) {
			return "redirect:/index";
		}

		return "common/cart.html";
	}

	@GetMapping("/cart/add/{id}")
	public String productoACarrito(@PathVariable("id") Long id, Model model) {

		productService.findById(id);

		shoppingCartService.addPurchaseLine(new PurchaseLine(productService.findById(id), 1, 1, null));

		return "redirect:/products/list";
	}

	@GetMapping("/cart/remove/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		shoppingCartService.removePurchaseLine(purchaseLineService.findById(id));
		return "redirect:/cart";
	}

	@ModelAttribute("cartTotal")
	public Double totalCart() {

		Map<PurchaseLine, Integer> carrito = shoppingCartService.getProductsInCart();
		double total = 0.0;
		if (carrito != null) {
			for (PurchaseLine p : carrito.keySet()) {
				total += p.getT().getPrice() * carrito.get(p);
			}
			return total;
		}

		return 0.0;
	}
}
