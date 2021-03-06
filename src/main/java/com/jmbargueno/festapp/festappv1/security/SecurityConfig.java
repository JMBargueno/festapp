/**
 * 
 */
package com.jmbargueno.festapp.festappv1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase con la configuracion de seguridad
 * @author jmbargueno
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/webjars/**","/fonts/**","/img/**", "/music/**", "/index", "/registro", "/registro/**",
				"/h2-console/**", "/products/","/searchList/**", "/products/**",
				"/contact/**", "/legal/**",
				"/images/**", "/party/**", "/cart/**")
				.permitAll().antMatchers("/admin/**")
				.hasAnyRole("ADMIN").anyRequest().authenticated()
				.and().formLogin().loginPage("/").permitAll()
				.and().logout()
				.logoutUrl("/logout")
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");

		// Añadimos esto para poder seguir accediendo a la consola de H2
		// con Spring Security habilitado.
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}
}