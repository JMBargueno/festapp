/**
 * 
 */
package com.jmbargueno.festapp.festappv1.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author jmbargueno
 *
 */
@Data
@NoArgsConstructor
@Entity
public class PartyType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String imgUrl;
	public PartyType(String name, String description, String imgUrl) {
		super();
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
	}
	
	
	
	

}
