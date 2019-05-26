/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.PartyType;
import com.jmbargueno.festapp.festappv1.repository.PartyTypeRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * @author jmbargueno
 *
 */
@Service
public class PartyTypeService extends BaseService<PartyType, Long, PartyTypeRepository> {

	@Autowired
	PartyTypeRepository partyTypeRepository;
	
	
	public PartyType findById(Long id) {
		
		return partyTypeRepository.findById(id).orElse(null);
	}

	public List<PartyType> findByName(String name) {
		return partyTypeRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<PartyType> findAllPageable(Pageable pageable) {
		return partyTypeRepository.findAll(pageable);
	}

	public Page<PartyType> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return partyTypeRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
