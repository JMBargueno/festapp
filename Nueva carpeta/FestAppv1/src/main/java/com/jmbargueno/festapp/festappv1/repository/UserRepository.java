package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<UserFA> findByNameContainingIgnoreCase(String name);

	public Page<UserFA> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
