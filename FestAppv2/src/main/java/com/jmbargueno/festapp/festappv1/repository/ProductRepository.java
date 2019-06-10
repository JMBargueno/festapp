/**
 * 
 */
package com.jmbargueno.festapp.festappv1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmbargueno.festapp.festappv1.model.Product;

/**
 * 
 * Repositorio de producto
 * 
 * @author jmbargueno
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

	/**
	 * Método que devuelve una búsqueda realizada por nombre y le tenemos que pasar
	 * el atributo por el cual deseemos buscar, en nuestro caso, por nombre ignorando las mayúsculas
	 * @param nombre Nombre del producto registrado en nuestra base de datos
	 * @return devuelve el producto buscado por nombre
	 */
	public List<Product> findByNameContainingIgnoreCase(String name);

	public Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
}
