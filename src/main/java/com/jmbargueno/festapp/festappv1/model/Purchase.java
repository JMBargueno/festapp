/**
 * 
 */
package com.jmbargueno.festapp.festappv1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private double finalPrice;

	private String ownerUserName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", orphanRemoval = true)
	private List<PurchaseLine> purchaseList = new ArrayList<>();

	@ManyToOne
	private UserFA userFA;

	/**
	 * @param date         Fecha de la compra.
	 * @param finalPrice   Precio final de la compra.
	 * @param purchaselist Lista de lineas de compra.
	 * @param userFA       Usuario que ha relalizado la compra.
	 * 
	 */
	

	/**
	 * Añade una Linea de compra a la compra y setea a la linea de compra su compra.
	 * 
	 * @param purchaseLine
	 */
	public void addPurchaseLine(PurchaseLine purchaseLine) {
		this.purchaseList.add(purchaseLine);
		purchaseLine.setPurchase(this);
	}

	/**
	 * Borra una linea de compra
	 * 
	 * @param purchaseLine
	 */
	public void removePurchaseLine(PurchaseLine purchaseLine) {
		this.purchaseList.remove(purchaseLine);
		purchaseLine.setPurchase(null);

	}

	/**
	 * Calcula el precio final de la compra con un bucle for sacando cada precio de
	 * cada linea de compra y sumandolos entre si haya el precio.
	 * 
	 * @return finalPrice Precio final de la compra
	 */
	public double calcFinalPrice() {
		double finalPrice = 0;
		for (PurchaseLine purchaseLine : purchaseList) {
			finalPrice += purchaseLine.getLinePrice();

		}

		return finalPrice;

	}

	public void setThisOwnerUserName() {
		this.setOwnerUserName(this.getUserFA().getUsername());
	}

}
