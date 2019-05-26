/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.repository.ConsumableRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de Consumable.
 * 
 * @author jmbargueno
 *
 */

@Service
public class ConsumableService extends BaseService<Consumable, Long, ConsumableRepository> implements IConsumableService {
	
	@Autowired
	ConsumableRepository consumableRepository;

	public List<Consumable> listConsumable() {

		List<Consumable> partialResult = repo.findAll();
		List<Consumable> result = new LinkedList<Consumable>(partialResult);

		for (int i = 0; i < partialResult.size(); i++) {
			String fileName = partialResult.get(i).getImgUrl();
			result.get(i).setImgUrl("/images/" + fileName);
		}

		return result;
	}

	public List<Consumable> listConsumableFormBean() {

		List<Consumable> list = repo.findAll();

		list = list.stream().map(p -> {
			p.setImgUrl("/images/" + p.getImgUrl());
			return p;
		}).collect(Collectors.toList());

		return list;
	}

	public Consumable findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return consumableRepository.findById(id).orElse(null);
	}

	public List<Consumable> findByName(String name) {
		return consumableRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<Consumable> findAllPageable(Pageable pageable) {
		return consumableRepository.findAll(pageable);
	}

	public Page<Consumable> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return consumableRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
