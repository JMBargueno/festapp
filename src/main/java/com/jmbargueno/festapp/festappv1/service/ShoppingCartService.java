package com.jmbargueno.festapp.festappv1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.model.PurchaseLine;
import com.jmbargueno.festapp.festappv1.repository.PurchaseLineRepository;

/**
 * 
 * Servicio del carrito, se usa con arrayList de lineas de pedidos.
 * 
 * @author Angel
 */

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

	private PurchaseLineRepository purchaseLineRepo;

	@Autowired
	private ProductService productService;

	private List<PurchaseLine> purchaseLines = new ArrayList<>();

	@Autowired
	PurchaseLineService purchaseLineService;

	@Autowired
	public ShoppingCartService(PurchaseLineRepository purchaseLineRepository) {
		this.purchaseLineRepo = purchaseLineRepository;
	}

	/**
	 * Si el producto ya está en el arrayList (en el carrito), solo se incrementará
	 * en uno la cantidad, una unidad más Si el producto no está en el en el
	 * arrayList, significa que no se ha comprado nada de él en este momento, por lo
	 * que se añade con cantidad 1
	 * 
	 * @param producto
	 */

	public void addPurchaseLine(Product p) {
		Boolean addedLine = false;

		for (PurchaseLine purchaseLine : purchaseLines) {
			if (purchaseLine.getT().getId() == p.getId()) {
				if (productService.areEnoughProducts(purchaseLine.getT(), purchaseLine.getQuantity() + 1)) {
					purchaseLine.setQuantity(purchaseLine.getQuantity() + 1);
					purchaseLine.setLinePrice(p.getPrice() * purchaseLine.getQuantity());
					addedLine = true;

				} else {
					purchaseLine.setQuantity(purchaseLine.getT().getStock());
					addedLine = true;
				}
			}
		}
		if (addedLine == false) {
			if (p.getStock() - 1 >= 0) {
				purchaseLines.add(new PurchaseLine(p, 1));

			}
		}
	}

	/**
	 * Método que elimina un producto del carrito. Borra la linea de pedido entera.
	 * 
	 * @param producto
	 */

	public void removePurchaseLine(PurchaseLine purchaseLine) {
		purchaseLines.remove(purchaseLine);
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista
	 */
	public List<PurchaseLine> getProductsInCart() {
		return Collections.unmodifiableList(purchaseLines);
	}

	/**
	 * Metodo para calcular el precio final
	 * 
	 * @return Precio final
	 */
	public double calcFinalPrice() {
		double totalPrice = 0;
		for (PurchaseLine purchaseLine : purchaseLines) {
			totalPrice += purchaseLine.getLinePrice();
		}
		return totalPrice;

	}

	/**
	 * Metodo que elimina todo el carrito
	 */
	public void resetCart() {
		purchaseLines.clear();
	}

}
