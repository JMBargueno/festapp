/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

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

	public Page<Event> findAllPageable(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}

}
