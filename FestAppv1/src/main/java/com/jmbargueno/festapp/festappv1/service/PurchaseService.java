/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.Ticket;
import com.jmbargueno.festapp.festappv1.repository.PurchaseRepository;
import com.jmbargueno.festapp.festappv1.repository.TicketRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;

/**
 * Servicio de Purchase.
 * 
 * @author jmbargueno
 *
 */

@Service
public class PurchaseService extends BaseService<Purchase, Long, PurchaseRepository> {

	@Autowired
	PurchaseRepository purchaseRepository;

	public Purchase findById(Long id) {

		return purchaseRepository.findById(id).orElse(null);
	}

	public Page<Purchase> findAllPageable(Pageable pageable) {
		return purchaseRepository.findAll(pageable);
	}

}
