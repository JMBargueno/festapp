package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Clase modelo de Producto.
 * 
 * @author jmbargueno
 *
 */
/**
 * @author jmbargueno
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class T {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Getter(AccessLevel.NONE)
	//@Setter(AccessLevel.NONE)
	
	private long id;

	@NotNull
	@Column(unique = true)
	private String name;
	private String description;
	@NotNull
	private double price;
	private int stock;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate eventDate;
	private String imgUrl;
	public T(long id, @NotNull String name, String description, @NotNull double price, int stock,
			LocalDate eventDate, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.eventDate = eventDate;
		this.imgUrl = imgUrl;
	}
	
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param eventDate
	 */
	
	


	



}
