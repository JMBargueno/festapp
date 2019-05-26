package com.jmbargueno.festapp.festappv1.controller;

import java.util.List;

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
	public String productoACarrito(@PathVariable("id") long id, Model model) {

		shoppingCartService.addPurchaseLine((id));

		return "redirect:/products/list";
	}

	@GetMapping("/cart/remove/{id}")
	public String removeProductFromCart(@PathVariable("id") long id) {

		int index = 0;

		List<PurchaseLine> list = shoppingCartService.getProductsInCart();

		for (PurchaseLine purchaseLine : list) {
			if (purchaseLine.getT() == productService.findById(id)) {
				index = list.indexOf(purchaseLine);
			}

		}

		shoppingCartService.removePurchaseLine(index);
		return "redirect:/cart";
	}

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
}
