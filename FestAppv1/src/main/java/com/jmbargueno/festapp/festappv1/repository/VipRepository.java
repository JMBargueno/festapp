/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Vip;

/**
 * Repositorio de Vip.
 * 
 * @author jmbargueno
 *
 */

@Repository
public interface VipRepository extends JpaRepository<Vip, Long> {
	
	public List<Vip> findByNameContainingIgnoreCase(String name);

	public Page<Vip> findByNameContainingIgnoreCase(String name, Pageable pageable);


}
