/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmbargueno.festapp.festappv1.formbean.SearchBean;
import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.ProductService;

/**
 * @author jmbargueno
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	PartyTypeService partyTypeService;

//	@GetMapping({"/list"})
//	public String allProducts(Model model) {
//		model.addAttribute("partiesList", partyTypeService.findAll());
//		model.addAttribute("productlist", productService.productListFormBean());
//		return "redirect:/searchList";
//	}

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE =12;
	private static final int[] PAGE_SIZES = { 8, 12, 16, 24, 40 };

	
	/**
	 * Controlador que lleva a lista de productos general
	 * 
	 * @param pageSize
	 * @param page
	 * @param nombre
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String showProductsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre,
			Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		String evalNombre = nombre.orElse(null);

		Page<Product> products = null;

		if (evalNombre == null) {
			products = productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			products = productService.findByNombreContainingIgnoreCasePageable(evalNombre,
					PageRequest.of(evalPage, evalPageSize));
		}

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		// Page<Producto> products =
		// productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("productos", products);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "buy/listAll.html";
	}

	@PostMapping("/search")
	public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("productos", productService.findByName(searchBean.getSearch()));

		return "buy/listAll.html";
	}
}
