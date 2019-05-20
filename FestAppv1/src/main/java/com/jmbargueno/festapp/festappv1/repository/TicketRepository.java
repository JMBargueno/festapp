/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Ticket;

/**
 * Repositorio de Ticket.
 * 
 * @author jmbargueno
 *
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	
}
