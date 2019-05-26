package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase modelo de Consumable.
 * 
 * @author jmbargueno
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("C")
@Entity

public class Consumable extends Product {

	private String mark;

	/**
	 * 
	 * @param id Id del consumible del usuario
	 * @param name Nombre del consumible
	 * @param description Descripcion del consumible
	 * @param price Precio del consumible
	 * @param stock Stock del producto
	 * @param eventDate Fecha del consumible
	 * @param imgUrl Url de la imagen del consumible
	 * @param mark Marca del consumible
	 */
	public Consumable(long id, @NotNull String name, String description, @NotNull double price, int stock,
			LocalDate eventDate, String imgUrl, String mark) {
		super(id, name, description, price, stock, eventDate, imgUrl);
		this.mark = mark;
	}

	public Consumable(long id, @NotNull String name, String description, @NotNull double price, int stock,
			LocalDate eventDate, String imgUrl) {
		super(id, name, description, price, stock, eventDate, imgUrl);
	}
	
	

	

}
