/**
 * 
 */
package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase modelo de Purchase.
 * 
 * @author jmbargueno
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	private double finalPrice;

	@OneToMany(mappedBy = "purchase")
	private List<PurchaseLine> purchaseList = new ArrayList<>();

	@ManyToOne
	private UserFA userFA;

	/**
	 * @param date         Fecha de la compra.
	 * @param finalPrice   Precio final de la compra.
	 * @param purchaselist Lista de lineas de compra.
	 * @param userFA         Usuario que ha relalizado la compra.
	 * 
	 */
	public Purchase(LocalDate date, double finalPrice, List<PurchaseLine> purchaseList, UserFA userFA) {
		super();
		this.date = date;
		this.finalPrice = finalPrice;
		this.purchaseList = purchaseList;
		this.userFA = userFA;
	}

}
