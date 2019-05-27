/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Purchase;

/**
 * Repositorio de Purchase.
 * 
 * @author jmbargueno
 *
 */

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	public List<Purchase> findByUserFA(long id);

	public Page<Purchase> findByUserFA(long id, Pageable pageable);
	
}
