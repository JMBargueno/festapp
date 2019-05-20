package com.jmbargueno.festapp.festappv1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmbargueno.festapp.festappv1.model.UserFA;

/**
 * Repositorio de User.
 * 
 * @author jmbargueno
 *
 */

@Repository
public interface UserRepository extends JpaRepository<UserFA, Long> {
	
	UserFA findFirstByUsername(String username);

}
