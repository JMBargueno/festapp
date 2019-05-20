/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Consumable;

/**
 * Repositorio de Consumable.
 * 
 * @author jmbargueno
 *
 */
@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {

	
	
	

}
