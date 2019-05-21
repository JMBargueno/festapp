/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.UserService;

/**
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

	@GetMapping("/registro")
	public String signUp(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("userform", new UserFA());
		return "common/register.html";
	}

	@PostMapping("/registro/submit")
	public String registroSubmit(@ModelAttribute("userform") UserFA userFA, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userFA.setPassword(passwordEncoder.encode(userFA.getPassword()));
		userService.save(userFA);

		return "redirect:/";
	}

}
