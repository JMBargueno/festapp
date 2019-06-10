/**
 * 
 */
package com.jmbargueno.festapp.festappv1.formbean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.jmbargueno.festapp.festappv1.model.Consumable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que modela el Modal Object que se recogerá del formulario menos la
 * imagen o fichero a subir, en este caso sencillo, solo la propiedadCualquiera
 * pero podrían ser más (nombre, fecha de caducidad...)
 *
 * @author jmbargueno
 * 
 */

@Data
@NoArgsConstructor
public class UploadFormBean {

	@NotNull
	@Column(unique = true)
	private String name;
	private String description;
	@NotNull
	private double price;
	private int stock;
	private String imgUrl;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate eventDate;

	// Consumable
	private String mark;

	// Ticket
	private int numTicket;

	// Vip
	private int numPersons;
	private int numVip;
	private Consumable consumeVip;

}
