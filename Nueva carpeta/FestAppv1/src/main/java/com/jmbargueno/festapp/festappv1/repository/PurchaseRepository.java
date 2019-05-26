/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Purchase;

/**
 * Repositorio de Repository.
 * 
 * @author jmbargueno
 *
 */

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
