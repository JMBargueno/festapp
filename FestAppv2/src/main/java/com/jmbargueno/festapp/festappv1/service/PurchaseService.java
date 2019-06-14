/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmbargueno.festapp.festappv1.model.Purchase;
import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.repository.PurchaseRepository;
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

	public Purchase findByIdd(Long id) {

		return purchaseRepository.findById(id).orElse(null);
	}

	public Page<Purchase> findById(long id, Pageable pageable) {
		return purchaseRepository.findById(id, pageable);
	}

	public Page<Purchase> findAllPageable(Pageable pageable) {
		return purchaseRepository.findAll(pageable);
	}

	public Page<Purchase> findByUserFA(UserFA user, Pageable pageable) {
		return purchaseRepository.findByUserFA(user, pageable);
	}

	public List<Purchase> findByUserFA(UserFA user) {
		return purchaseRepository.findByUserFA(user);
	}
	
	

	public double calcAllPurchases(UserFA user, int opt) {
		double sumTotal = 0;
		List<Purchase> listOfUser;
		switch (opt) {
		case 0:
			listOfUser = this.findByUserFA(user);
			for (Purchase purchase : listOfUser) {
				sumTotal += purchase.getFinalPrice();
			}

			break;
		case 1:
			
			break;

		case 2:
			break;
		default:
			listOfUser = this.findByUserFA(user);
			for (Purchase purchase : listOfUser) {
				sumTotal += purchase.getFinalPrice();
			}
			break;
		}

		return sumTotal;
	}

}
