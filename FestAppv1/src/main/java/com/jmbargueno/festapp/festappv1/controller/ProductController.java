/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmbargueno.festapp.festappv1.formbean.SearchBean;
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
	
	@PostMapping("/search")
	  public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
		model.addAttribute("partiesList", partyTypeService.findAll());
	  	model.addAttribute("productos", productService.findByName(searchBean.getSearch()));
	  
	  return "buy/listAll.html";
	  }
}
