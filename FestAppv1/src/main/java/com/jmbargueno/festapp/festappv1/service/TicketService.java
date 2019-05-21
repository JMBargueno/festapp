/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;


import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Ticket;
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



}
