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
import org.springframework.web.bind.annotation.RequestParam;

import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.ProductService;

/**
 * @author jmbargueno
 *
 */
@Controller
public class PagerController {


	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@Autowired
	PartyTypeService partyTypeService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/searchList")
	public String showProductsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre, Model model) {
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
        	products = productService.findByNombreContainingIgnoreCasePageable(evalNombre, PageRequest.of(evalPage, evalPageSize));
        }

        // Obtenemos la página definida por evalPage y evalPageSize de objetos de nuestro modelo
        //Page<Producto> products = productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        // Creamos el objeto Pager (paginador) indicando los valores correspondientes.
        // Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos botones
        // debe mostrar y cuál es el número de objetos a dibujar.
        Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);
        
        model.addAttribute("productos", products);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        
    	return "buy/listAll.html";
    }
	
}
