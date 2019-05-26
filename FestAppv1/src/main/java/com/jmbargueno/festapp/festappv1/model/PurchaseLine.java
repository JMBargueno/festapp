/**
 * 
 */
package com.jmbargueno.festapp.festappv1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase modelo de PurchaseLine.
 * 
 * @author jmbargueno
 *
 */

@Data
@NoArgsConstructor
@Entity
public class PurchaseLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private long Id;
	@ManyToOne
	private Product t;
	@NotNull
	private int quantity;
	@NotNull
	private double linePrice;

	@ManyToOne
	private Purchase purchase;

	/**
	 * @param t         Objeto tipo producto.
	 * @param quantity  Cantidad del producto
	 * @param linePrice Precio final de la linea de producto
	 * @param purchase  Compra donde va la linea de compra.
	 */
	public PurchaseLine(Product t, int quantity) {
		super();
		this.t = t;
		this.quantity = quantity;
		this.linePrice = calcLinePrice();
		
	}

	public void addProduct(Product product) {
		this.setT(product);
	}

	public void removeProduct(Product product) {
		this.setT(null);

		;
	}

	public double calcLinePrice() {
		return this.getT().getPrice() * this.getQuantity();

	}

}
