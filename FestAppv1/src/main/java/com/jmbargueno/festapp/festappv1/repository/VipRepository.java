/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;


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


}
