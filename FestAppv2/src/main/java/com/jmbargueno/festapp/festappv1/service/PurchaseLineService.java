/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.PurchaseLine;
import com.jmbargueno.festapp.festappv1.repository.PurchaseLineRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de PurchaseLine.
 * 
 * @author jmbargueno
 *
 */

@Service
public class PurchaseLineService extends BaseService<PurchaseLine, Long, PurchaseLineRepository> {

	@Autowired
	PurchaseLineRepository purchaseLineRepository;

	public PurchaseLine findById(Long id) {

		return purchaseLineRepository.findById(id).orElse(null);
	}

	public Page<PurchaseLine> findById(long id, Pageable pageable) {
		return purchaseLineRepository.findById(id, pageable);
	}

	public Page<PurchaseLine> findAllPageable(Pageable pageable) {
		return purchaseLineRepository.findAll(pageable);
	}

	public Page<PurchaseLine> findByPurchase(Purchase purchase, Pageable pageable) {
		return purchaseLineRepository.findByPurchase(purchase, pageable);
	}
	
}
