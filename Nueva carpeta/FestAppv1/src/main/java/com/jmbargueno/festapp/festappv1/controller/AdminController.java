/**
 * 
 */
package com.jmbargueno.festapp.festappv1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jmbargueno.festapp.festappv1.formbean.UploadFormBean;
import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.model.Event;
import com.jmbargueno.festapp.festappv1.model.Pager;
import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.model.Ticket;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.model.Vip;
import com.jmbargueno.festapp.festappv1.service.ConsumableService;
import com.jmbargueno.festapp.festappv1.service.EventService;
import com.jmbargueno.festapp.festappv1.service.PartyTypeService;
import com.jmbargueno.festapp.festappv1.service.TicketService;
import com.jmbargueno.festapp.festappv1.service.UploadService;
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
	@Autowired
	UploadService uploadService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	// Consumibles
	@GetMapping("/consumables")
	public String showConsumable(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<Consumable> consumables = null;

		if (evalNombre == null) {
			consumables = consumableService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			consumables = consumableService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(consumables.getTotalPages(), consumables.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("consumable", consumables);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "admin/tables/consumables.html";
	}

	@GetMapping("/addConsumable")
	public String addConsumable(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("consumableform", new Consumable());
		return "admin/add/addConsumable.html";
	}

	@PostMapping("/addConsumable/submit")
	public String addConsumableSubmit(@ModelAttribute("consumableform") UploadFormBean uploadFormBean, Model model,
			@RequestParam("file") MultipartFile file) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Consumable consumable = new Consumable();
		consumable.setName(uploadFormBean.getName());
		consumable.setDescription(uploadFormBean.getDescription());
		consumable.setPrice(uploadFormBean.getPrice());
		consumable.setStock(uploadFormBean.getStock());
		consumable.setEventDate(uploadFormBean.getEventDate());
		consumable.setMark(uploadFormBean.getMark());
		uploadService.add(consumable, file);

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
	public String showTickets(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<Ticket> tickets = null;

		if (evalNombre == null) {
			tickets = ticketService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			tickets = ticketService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(tickets.getTotalPages(), tickets.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("ticket", tickets);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "admin/tables/tickets.html";
	}

	@GetMapping("/addTicket")
	public String addTicket(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("ticketform", new Ticket());
		return "admin/add/addTicket.html";
	}

	@PostMapping("/addTicket/submit")
	public String addTicketSubmit(@ModelAttribute("ticketform") UploadFormBean uploadFormBean, Model model,
			@RequestParam("file") MultipartFile file) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Ticket ticket = new Ticket();
		ticket.setName(uploadFormBean.getName());
		ticket.setDescription(uploadFormBean.getDescription());
		ticket.setPrice(uploadFormBean.getPrice());
		ticket.setStock(uploadFormBean.getStock());
		ticket.setEventDate(uploadFormBean.getEventDate());
		ticket.setNumTicket(uploadFormBean.getNumTicket());
		uploadService.add(ticket, file);

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
	public String showVips(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<Vip> vips = null;

		if (evalNombre == null) {
			vips = vipService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			vips = vipService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(vips.getTotalPages(), vips.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("vip", vips);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "admin/tables/vips.html";
	}

	@GetMapping("/addVip")
	public String addVip(Model model) {
		model.addAttribute("partiesList", partyTypeService.findAll());
		model.addAttribute("vipform", new Vip());
		return "admin/add/addVip.html";
	}

	@PostMapping("/addVip/submit")
	public String addTVipSubmit(@ModelAttribute("vipform") UploadFormBean uploadFormBean, Model model,
			@RequestParam("file") MultipartFile file) {
		model.addAttribute("partiesList", partyTypeService.findAll());

		Vip vip = new Vip();
		vip.setName(uploadFormBean.getName());
		vip.setDescription(uploadFormBean.getDescription());
		vip.setPrice(uploadFormBean.getPrice());
		vip.setStock(uploadFormBean.getStock());
		vip.setEventDate(uploadFormBean.getEventDate());
		vip.setNumPersons(uploadFormBean.getNumPersons());
		vip.setNumVip(uploadFormBean.getNumVip());
		vip.setConsumeVip(uploadFormBean.getConsumeVip());
		uploadService.add(vip, file);

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
	public String showUsers(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<UserFA> users = null;

		if (evalNombre == null) {
			users = userService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			users = userService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("user", users);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
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
	public String showParties(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<PartyType> parties = null;

		if (evalNombre == null) {
			parties = partyTypeService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			parties = partyTypeService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(parties.getTotalPages(), parties.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("partyType", parties);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

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
	public String showEvents(@RequestParam("pageSize") Optional<Integer> pageSize,
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

		Page<Event> events = null;

		if (evalNombre == null) {
			events = eventService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		} else {
			events = eventService.findByNombreContainingIgnoreCasePageable(evalNombre,
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
		Pager pager = new Pager(events.getTotalPages(), events.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("event", events);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "admin/tables/events.html";
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
