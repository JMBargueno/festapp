/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Ticket;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.repository.TicketRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de Ticket.
 * 
 * @author jmbargueno
 *
 */

@Service
public class TicketService extends BaseService<Ticket, Long, TicketRepository> {

	@Autowired
	TicketRepository ticketRepository;
	
	
	
	public Ticket findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return ticketRepository.findById(id).orElse(null);
	}

	public List<Ticket> findByName(String name) {
		return ticketRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<Ticket> findAllPageable(Pageable pageable) {
		return ticketRepository.findAll(pageable);
	}

	public Page<Ticket> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return ticketRepository.findByNameContainingIgnoreCase(name, pageable);
	}


}
