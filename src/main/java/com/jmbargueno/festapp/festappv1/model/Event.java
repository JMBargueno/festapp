/**
 * 
 */
package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import lombok.NoArgsConstructor;



/**
 * Clase modelo de evento
 * @author jmbargueno
 *
 */


@Data
@NoArgsConstructor
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventDate;
	private String imgUrl;
	
	/**
	 * 
	 * @param name Nombre del evento
	 * @param description Descripcion del evento
	 * @param eventDate Fecha del evento
	 * @param imgUrl Url de la imagen el evento
	 */
	
	public Event(String name, String description, LocalDate eventDate, String imgUrl) {
		super();
		this.name = name;
		this.description = description;
		this.eventDate = eventDate;
		this.imgUrl = imgUrl;
	}
	
	
	
	

}
