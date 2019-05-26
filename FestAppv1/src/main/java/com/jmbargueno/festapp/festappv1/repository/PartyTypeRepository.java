/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmbargueno.festapp.festappv1.model.PartyType;

/**
 * @author jmbargueno
 *
 */
public interface PartyTypeRepository extends JpaRepository<PartyType, Long> {

	public List<PartyType> findByNameContainingIgnoreCase(String name);

	public Page<PartyType> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
}
