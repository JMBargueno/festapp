/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jmbargueno.festapp.festappv1.model.Product;

/**
 * @author jmbargueno
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

}
