/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

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

	@Override
	public Ticket save(Ticket t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}

	@Override
	public Ticket findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public Ticket edit(Ticket t) {
		// TODO Auto-generated method stub
		return super.edit(t);
	}

	@Override
	public void delete(Ticket t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
