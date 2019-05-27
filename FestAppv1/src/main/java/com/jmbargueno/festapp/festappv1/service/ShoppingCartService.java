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
 * @author Ángel N
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

		Boolean agregarAldv = false;
		for (PurchaseLine purchaseLine : purchaseLines) {
			if (purchaseLine.getT().getId() == p.getId()) {
				purchaseLine.setQuantity(purchaseLine.getQuantity() + 1);
				agregarAldv = true;
			}
		}
		if (agregarAldv == false) {
			purchaseLines.add(new PurchaseLine(p, 1));
		}

	}

	/**
	 * Método que elimina un producto del carrito. Borra la linea de pedido entera.
	 * 
	 * @param producto
	 */

	public void removePurchaseLine(int index) {
		purchaseLines.remove(index);
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista
	 */

	public List<PurchaseLine> getProductsInCart() {
		return Collections.unmodifiableList(purchaseLines);
	}

	public double calcFinalPrice() {
		double totalPrice = 0;
		for (PurchaseLine purchaseLine : purchaseLines) {
			totalPrice += purchaseLine.getLinePrice();
		}
		return totalPrice;

	}
	
	public void resetCart() {
		purchaseLines.clear();
	}
	
	

}
