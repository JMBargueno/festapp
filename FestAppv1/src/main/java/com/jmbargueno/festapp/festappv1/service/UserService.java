package com.jmbargueno.festapp.festappv1.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.model.Vip;
import com.jmbargueno.festapp.festappv1.repository.UserRepository;
import com.jmbargueno.festapp.festappv1.repository.VipRepository;
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
	
	public Page<UserFA> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
	
	public UserFA searchByUsername(String username) {
		return repo.findFirstByUsername(username);
	}

	
	
	

}
