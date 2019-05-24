package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase modelo de User.
 * 
 * @author jmbargueno
 *
 */

@Data
@NoArgsConstructor
@Entity
public class UserFA {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;
	private String surname;
	private Character gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	@Column(unique = true)
	private String dni;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	private String password;

	@NotNull
	@Column(unique = true)
	private String email;
	
	
	private String phoneNumber;

	@NotNull
	private boolean isAdmin;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registerDate;

	@OneToMany(mappedBy = "userFA")
	private List<Purchase> purchase = new ArrayList<>();

	/**
	 * @param name         Nombre del usuario.
	 * @param surname      Apellidos del usuario.
	 * @param gender       Género del usuario.
	 * @param birthday     Fecha de nacimiento del usuario.
	 * @param dni          Documento nacional de identidad del usuario.
	 * @param username     Nick elegido por el usuario.
	 * @param passwd       Contraseña del usuario.
	 * @param email        Correo del usuario.
	 * @param isAdmin      Booleano para saber si el usuario es administrador.
	 * @param registerDate Fecha de registro del usuario.
	 * @param purchase     ArrayList de las compras que ha realizado el usuario.
	 */

	// Metodo constructor
	public UserFA(@NotNull String name, String surname, Character gender, LocalDate birthday, String dni,
			@NotNull String username, @NotNull String password, @NotNull String email, @NotNull boolean isAdmin,
			LocalDate registerDate, List<Purchase> purchase) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.dni = dni;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
		this.registerDate = registerDate;
		this.purchase = purchase;
	}

}
