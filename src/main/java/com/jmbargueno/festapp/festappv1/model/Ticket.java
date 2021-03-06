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
 * Clase modelo de Ticket.
 * 
 * @author jmbargueno
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("T")
@Entity

public class Ticket extends Product {

	private int numTicket;

	
	/**
	 * 
	 * @param id Id de la entrada
	 * @param name Nombre de la entrada
	 * @param description Descripción de la entrada
	 * @param price Precio de la entrada
	 * @param stock Cantidad de entradas disponibles
	 * @param eventDate Fecha de la entrada
	 * @param imgUrl Imagen de la entrada
	 * @param numTicket Numero de entrada
	 */
	public Ticket(long id, @NotNull String name, String description, @NotNull double price, int stock,
			LocalDate eventDate, String imgUrl, int numTicket) {
		super(id, name, description, price, stock, eventDate, imgUrl);
		this.numTicket = numTicket;
	}

	
	


	
	


}
