/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.UserFA;

/**
 * Repositorio de Purchase.
 * 
 * @author jmbargueno
 *
 */

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	public List<Purchase> findByUserFA(UserFA user);

	public Page<Purchase> findByUserFA(UserFA user, Pageable pageable);

	public List<Purchase> findById(long id);

	public Page<Purchase> findById(long id, Pageable pageable);

	@Query("select p from Purchase p where (userFA = :fuser) and (extract (month from date) = extract( month from sysdate)) and (extract (year from date) = extract( year from sysdate))")
	public List<Purchase> findUserAndMonth(@Param("fuser") UserFA user);

	@Query("select p from Purchase p where (userFA = :fuser) and (extract (month from date) = extract( month from sysdate)) and (extract (year from date) = extract( year from sysdate))")
	public Page<Purchase> findUserAndMonth(@Param("fuser") UserFA user, Pageable pageable);

	@Query("select p from Purchase p where (userFA = :fuser) and (extract (year from date) = extract( year from sysdate))")
	public List<Purchase> findUserAndYear(@Param("fuser") UserFA user);

	@Query("select p from Purchase p where (userFA = :fuser) and (extract (year from date) = extract( year from sysdate))")
	public Page<Purchase> findUserAndYear(@Param("fuser") UserFA user, Pageable pageable);

}
