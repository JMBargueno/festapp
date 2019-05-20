/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

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

}
