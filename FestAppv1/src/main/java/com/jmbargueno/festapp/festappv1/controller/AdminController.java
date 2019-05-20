/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.model.Event;
import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.model.Ticket;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.model.Vip;
import com.jmbargueno.festapp.festappv1.service.ConsumableService;
import com.jmbargueno.festapp.festappv1.service.EventService;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.TicketService;
import com.jmbargueno.festapp.festappv1.service.UserService;
import com.jmbargueno.festapp.festappv1.service.VipService;

/**
 * @author jmbargueno
 *
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ConsumableService consumableService;
	@Autowired
	TicketService ticketService;
	@Autowired
	VipService vipService;
	@Autowired
	UserService userService;
	@Autowired
	PartyTypeService partyTypeService;
	@Autowired
	EventService eventService;

	// Consumibles
	@GetMapping("/consumables")
	public String showConsumables(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("consumablesList", consumableService.findAll());
		return "admin/tables/consumables.html";
	}

	@GetMapping("/addConsumable")
	public String addConsumable(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("consumableform", new Consumable());
		return "admin/add/addConsumable.html";
	}

	@PostMapping("/addConsumable/submit")
	public String addConsumableSubmit(@ModelAttribute("consumableform") Consumable consumable, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		consumableService.save(consumable);

		return "redirect:/admin/consumables";
	}

	@GetMapping("/editConsumable/{id}")
	public String editConsumable(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		Consumable editedConsumable = consumableService.findById(id);

		if (editedConsumable != null) {
			model.addAttribute("consumableform", editedConsumable);
			return "admin/edit/editConsumable";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/consumables";
		}
	}

	@PostMapping("/editConsumable/submit")
	public String editConsumableSubmit(@ModelAttribute("consumableform") Consumable consumable, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		consumableService.edit(consumable);
		return "redirect:/admin/consumables";
	}

	@GetMapping("/delConsumable/{id}")
	public String delConsumable(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		consumableService.deleteById(id);
		return "redirect:/admin/consumables";
	}

	////////////////////////////////////////////////////////////

	// Tickets
	@GetMapping("/tickets")
	public String showTickets(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("ticketsList", ticketService.findAll());
		return "admin/tables/tickets.html";
	}

	@GetMapping("/addTicket")
	public String addTicket(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("ticketform", new Ticket());
		return "admin/add/addTicket.html";
	}

	@PostMapping("/addTicket/submit")
	public String addTicketSubmit(@ModelAttribute("ticketform") Ticket ticket, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		ticketService.save(ticket);

		return "redirect:/admin/tickets";
	}

	@GetMapping("/editTicket/{id}")
	public String editTicket(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Ticket editedTicket = ticketService.findById(id);

		if (editedTicket != null) {
			model.addAttribute("ticketform", editedTicket);
			return "admin/edit/editTicket";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/tickets";
		}
	}

	@PostMapping("/editTicket/submit")
	public String editTicketSubmit(@ModelAttribute("consumableform") Ticket ticket, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		ticketService.edit(ticket);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/delTicket/{id}")
	public String delTicket(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		ticketService.deleteById(id);
		return "redirect:/admin/tickets";
	}

////////////////////////////////////////////////////////////

	// Reservados
	@GetMapping("/vips")
	public String showVips(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("vipsList", vipService.findAll());
		return "admin/tables/vips.html";
	}

	@GetMapping("/addVip")
	public String addVip(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("vipform", new Vip());
		return "admin/add/addVip.html";
	}

	@PostMapping("/addVip/submit")
	public String addTVipSubmit(@ModelAttribute("vipform") Vip vip, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		vipService.save(vip);

		return "redirect:/admin/vips";
	}

	@GetMapping("/editVip/{id}")
	public String editVip(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Vip editedVip = vipService.findById(id);

		if (editedVip != null) {
			model.addAttribute("vipform", editedVip);
			return "admin/edit/editVip";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/vips";
		}
	}

	@PostMapping("/editVip/submit")
	public String editVipSubmit(@ModelAttribute("vipform") Vip vip, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		vipService.edit(vip);
		return "redirect:/admin/vips";
	}

	@GetMapping("/delVip/{id}")
	public String delVip(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		vipService.deleteById(id);
		return "redirect:/admin/vips";
	}
////////////////////////////////////////////////////////////

	// Usuarios

	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("usersList", userService.findAll());
		return "admin/tables/users.html";
	}

	@GetMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("userform", new UserFA());
		return "admin/add/addUser.html";
	}

	@PostMapping("addUser/submit")
	public String addUserSubmit(@ModelAttribute("userform") UserFA userFA, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		userService.save(userFA);

		return "redirect:/admin/users";
	}

	@GetMapping("/editUser/{id}")
	public String editUser(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		UserFA editedUser = userService.findById(id);

		if (editedUser != null) {
			model.addAttribute("userform", editedUser);
			return "admin/edit/editUser";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/users";
		}
	}

	@PostMapping("/editUser/submit")
	public String editConsumableSubmit(@ModelAttribute("userform") UserFA userFA, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		userFA.setPassword(passwordEncoder.encode(userFA.getPassword()));
		
		userService.edit(userFA);
		return "redirect:/admin/users";
	}

	@GetMapping("/delUser/{id}")
	public String delUser(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		userService.deleteById(id);
		return "redirect:/admin/users";
	}

////////////////////////////////////////////////////////////

	// PartyType
	@GetMapping("/parties")
	public String showpartyTypes(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("partiesList", partyTypeService.findAll());
		return "admin/tables/parties.html";
	}

	@GetMapping("/addParty")
	public String addParty(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("partyform", new PartyType());
		return "admin/add/addParty.html";
	}

	@PostMapping("addParty/submit")
	public String addUserSubmit(@ModelAttribute("partyform") PartyType party, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		partyTypeService.save(party);

		return "redirect:/admin/parties";
	}

	@GetMapping("/editParty/{id}")
	public String editParty(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		PartyType editedParty = partyTypeService.findById(id);

		if (editedParty != null) {
			model.addAttribute("partyform", editedParty);
			return "admin/edit/editParty";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/parties";
		}
	}

	@PostMapping("/editParty/submit")
	public String editPartySubmit(@ModelAttribute("partyform") PartyType party, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		partyTypeService.edit(party);
		return "redirect:/admin/parties";
	}

	@GetMapping("/delParty/{id}")
	public String delParty(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		partyTypeService.deleteById(id);
		return "redirect:/admin/parties";
	}

////////////////////////////////////////////////////////////
	// Events
	@GetMapping("/events")
	public String showEvents(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("eventsList", eventService.findAll());
		return "admin/tables/events.html";
	}

	@GetMapping("/addEvent")
	public String addEvent(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("eventform", new Event());
		return "admin/add/addEvent.html";
	}

	@PostMapping("/addEvent/submit")
	public String addEventSubmit(@ModelAttribute("eventform") Event event, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		eventService.save(event);

		return "redirect:/admin/events";
	}

	@GetMapping("/editEvent/{id}")
	public String editEvent(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Event editedEvent = eventService.findById(id);

		if (editedEvent != null) {
			model.addAttribute("eventform", editedEvent);
			return "admin/edit/editEvent";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/admin/events";
		}
	}

	@PostMapping("/editEvent/submit")
	public String editConsumableSubmit(@ModelAttribute("eventform") Event event, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		eventService.edit(event);
		return "redirect:/admin/events";
	}

	@GetMapping("/delEvent/{id}")
	public String delEvent(@PathVariable("id") long id, Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		eventService.deleteById(id);
		return "redirect:/admin/events";
	}

}
