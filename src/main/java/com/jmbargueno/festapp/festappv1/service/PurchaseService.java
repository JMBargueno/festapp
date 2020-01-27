/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.List;
import java.util.Optional;

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

	public List<Purchase> findByUserAndMonth(UserFA user) {
		return purchaseRepository.findUserAndMonth(user);
	}

	public Page<Purchase> findByUserAndMonth(UserFA user, Pageable pageable) {
		return purchaseRepository.findUserAndMonth(user, pageable);
	}

	public List<Purchase> findByUserAndYear(UserFA user) {
		return purchaseRepository.findUserAndYear(user);
	}

	public Page<Purchase> findByUserAndYear(UserFA user, Pageable pageable) {
		return purchaseRepository.findUserAndYear(user, pageable);
	}

	public double calcAllPurchases(UserFA user, Optional<Integer> opt) {
		double sumTotal = 0;
		List<Purchase> listOfUser;

		int evalOpt = opt.orElse(0);

		if (user != null) {

			switch (evalOpt) {
			case 0:
				listOfUser = this.findByUserFA(user);
				for (Purchase purchase : listOfUser) {
					sumTotal += purchase.getFinalPrice();
				}

				break;
			case 1:
				listOfUser = this.findByUserAndMonth(user);
				for (Purchase purchase : listOfUser) {
					sumTotal += purchase.getFinalPrice();
				}
				break;

			case 2:
				listOfUser = this.findByUserAndYear(user);
				for (Purchase purchase : listOfUser) {
					sumTotal += purchase.getFinalPrice();
				}
				break;
			default:
				listOfUser = this.findByUserFA(user);
				for (Purchase purchase : listOfUser) {
					sumTotal += purchase.getFinalPrice();
				}
				break;
			}
		}

		return sumTotal;
	}

}
