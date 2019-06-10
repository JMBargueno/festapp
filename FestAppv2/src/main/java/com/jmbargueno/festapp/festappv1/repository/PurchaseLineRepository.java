/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.PurchaseLine;

/**
 * Repositorio de PurchaseLine.
 * 
 * @author jmbargueno
 *
 */
@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Long>{

	

}
