/**
 * 
 */
package com.jmbargueno.festapp.festappv1.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.UserService;

/**
 * Clase que implementa el usuario de seguridad
 * 
 * @author jmbargueno
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	UserService userService;

	public UserDetailsServiceImpl(UserService servicio) {
		this.userService = servicio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		
		UserFA usuario = userService.searchByUsername(username);

		UserBuilder userBuilder = null;

		if (usuario != null) {
			userBuilder = User.withUsername(usuario.getUsername());
			userBuilder.disabled(false);
			userBuilder.password(usuario.getPassword());
			if (usuario.isAdmin()) {
				// Este caso indica que un ADMIN también puede hacer todo lo que hace un USER
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"),
						new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			}
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return userBuilder.build();

	}

}