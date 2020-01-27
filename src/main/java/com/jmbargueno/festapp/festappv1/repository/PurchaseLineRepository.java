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
import com.jmbargueno.festapp.festappv1.model.PurchaseLine;

/**
 * Repositorio de PurchaseLine.
 * 
 * @author jmbargueno
 *
 */
@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Long>{

	public List<PurchaseLine> findByPurchase(Purchase purchase);
	public Page<PurchaseLine> findByPurchase(Purchase purchase, Pageable pageable);
	
	public List<PurchaseLine> findById(long id);
	public Page<PurchaseLine> findById(long id, Pageable pageable);

}
