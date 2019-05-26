/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Vip;
import com.jmbargueno.festapp.festappv1.repository.VipRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de Vip.
 * 
 * @author jmbargueno
 *
 */
@Service
public class VipService extends BaseService<Vip, Long, VipRepository> {

	@Autowired
	VipRepository vipRepository;

	public Vip findById(Long id) {
		return vipRepository.findById(id).orElse(null);
	}

	public List<Vip> findByName(String name) {
		return vipRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<Vip> findAllPageable(Pageable pageable) {
		return vipRepository.findAll(pageable);
	}

	public Page<Vip> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return vipRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
