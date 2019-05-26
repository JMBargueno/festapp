package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase modelo de Vip.
 * 
 * @author jmbargueno
 *
 */

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("V")
@Entity

public class Vip extends Product {

	@NotNull
	private int numPersons;
	private int numVip;
	@ManyToOne
	private Consumable consumeVip;

	/**
	 * @param id Id del reservado
	 * @param name Nombre del reservado
	 * @param description Descripción del reservado
	 * @param price Precio del reservado
	 * @param stock Stock del reservado
	 * @param eventDate Fecha de evento del reservado
	 * @param imgUrl Imagen del reservado
	 * @param numPersons Numero del personas en el reservado
	 * @param numVip Numero del reservado
	 * @param consumeVip Consumible asignados al reservado
	 */
	public Vip(long id, @NotNull String name, String description, @NotNull double price, int stock, LocalDate eventDate,
			String imgUrl, @NotNull int numPersons, int numVip, Consumable consumeVip) {
		super(id, name, description, price, stock, eventDate, imgUrl);
		this.numPersons = numPersons;
		this.numVip = numVip;
		this.consumeVip = consumeVip;
	}

}