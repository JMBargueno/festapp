/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Event;
import com.jmbargueno.festapp.festappv1.repository.EventRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * @author jmbargueno
 *
 */
@Service
public class EventService extends BaseService<Event, Long, EventRepository> {
	@Autowired
	EventRepository eventRepository;

	public Event findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return eventRepository.findById(id).orElse(null);
	}

	public List<Event> findByName(String name) {
		return eventRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<Event> findAllPageable(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}

	public Page<Event> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return eventRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
