package com.jmbargueno.festapp.festappv1.service;



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

	public UserFA searchByUsername(String username) {
		return repo.findFirstByUsername(username);
	}

	
	
	

}
