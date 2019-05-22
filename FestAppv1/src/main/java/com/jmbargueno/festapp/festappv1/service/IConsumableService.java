/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jmbargueno.festapp.festappv1.model.Consumable;

/**
 * @author jmbargueno
 *
 */
public interface IConsumableService {

	/**
     * Encuenta una "página" de personas
     *
     * @param pageable
     * @return {@link Page} instance
     */
    Page<Consumable> findAllPageable(Pageable pageable);
}
