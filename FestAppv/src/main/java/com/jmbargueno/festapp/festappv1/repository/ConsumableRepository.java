/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<Consumable> findByNameContainingIgnoreCase(String name);

	public Page<Consumable> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
