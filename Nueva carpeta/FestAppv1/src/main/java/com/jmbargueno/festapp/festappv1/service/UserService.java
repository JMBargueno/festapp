package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.repository.UserRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de User.
 * 
 * @author jmbargueno
 *
 */
@Service
public class UserService extends BaseService<UserFA, Long, UserRepository> {

	@Autowired
	UserRepository userRepository;

	public UserFA searchByUsername(String username) {
		return repo.findFirstByUsername(username);
	}

	public List<UserFA> userListFormBean() {

		List<UserFA> lista = userRepository.findAll();

//		lista = lista.stream().map(p -> {
//			p.setImgUrl("/images/" + p.getImgUrl());
//			return p;
//		}).collect(Collectors.toList());

		return lista;
	}

	public UserFA findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return userRepository.findById(id).orElse(null);
	}

	public List<UserFA> findByName(String name) {
		return userRepository.findByNameContainingIgnoreCase(name);
	}

	public Page<UserFA> findAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<UserFA> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable) {
		return userRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
