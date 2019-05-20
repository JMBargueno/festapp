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
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param eventDate
	 * @param imgUrl
	 * @param mark
	 */
	public Consumable(long id, @NotNull String name, String description, @NotNull double price, int stock,
			LocalDate eventDate, String imgUrl, String mark) {
		super(id, name, description, price, stock, eventDate, imgUrl);
		this.mark = mark;
	}

	

}
