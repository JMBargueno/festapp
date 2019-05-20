


package com.jmbargueno.festapp.festappv1.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jmbargueno
 *
 */
public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

	@Autowired
	protected R repo;

	/**
	 * Almacenamos una nueva entidad a través del repositorio
	 * 
	 * @param t tipo
	 * @return t
	 */
	public T save(T t) {
		return repo.save(t);
	}

	/**
	 * Localizamos una entidad en base a su Id
	 * 
	 * @param id id
	 * @return id
	 */ 
	public T findById(ID id) {
		return repo.findById(id).orElse(null);
	}

	/**
	 * Obtenemos todas las entidades de un tipo de entidad
	 * 
	 * @return id
	 */
	public List<T> findAll() {
		return repo.findAll();
	}

	/**
	 * Editamos una instancia de una entidad (si no tiene Id, la insertamos).
	 * 
	 * @param t id
	 * @return id
	 */
	public T edit(T t) {
		return repo.save(t);
	}

	/**
	 * Eliminamos una instancia de una entidad
	 * 
	 * @param t id
	 */
	public void delete(T t) {
		repo.delete(t);
	}

	/**
	 * Eliminamos una instancia en base a su ID
	 * 
	 * @param id id
	 */
	public void deleteById(ID id) {
		repo.deleteById(id);
	}

}
