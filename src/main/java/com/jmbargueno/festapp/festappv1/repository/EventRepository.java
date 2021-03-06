/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Event;

/**
 * 
 * Repositorio de evento
 * 
 * @author jmbargueno
 *
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	public List<Event> findByNameContainingIgnoreCase(String name);

	public Page<Event> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
