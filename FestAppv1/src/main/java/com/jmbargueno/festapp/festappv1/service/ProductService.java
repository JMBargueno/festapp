/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.repository.ProductRepository;
import com.jmbargueno.festapp.festappv1.service.base.BaseService;
import com.jmbargueno.festapp.festappv1.storage.StorageService;

/**
 * @author jmbargueno
 *
 */
@Service
public class ProductService extends BaseService<Product, Long, ProductRepository> {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StorageService storageService;

	public void add(Product p, MultipartFile file) {
		

		String fileName = storageService.store(file);// Guarda la imagen
		// Guardamos nombre de la imagen almacenada en el atributo de la entidad
		p.setImgUrl(fileName);
		// Guardamos la entidad en la base de datos y en ella ya irá el nombre del
		// archivo
		// en la correspondiente propiedad (fileUrl)
		productRepository.save(p);

	}

	public List<Product> list() {

		List<Product> partialResult = productRepository.findAll();
		List<Product> result = new LinkedList<Product>(partialResult);

		for (int i = 0; i < partialResult.size(); i++) {
			String fileName = partialResult.get(i).getImgUrl();
			result.get(i).setImgUrl(fileName);
		}

		return result;
	}

	public List<Product> productListFormBean() {

		List<Product> lista = productRepository.findAll();

		lista = lista.stream().map(p -> {
			p.setImgUrl("/images/" + p.getImgUrl());
			return p;
		}).collect(Collectors.toList());

		return lista;
	}
	
	public Product findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return productRepository.findById(id).orElse(null);
	}

	public List<Product> findByName(String name){
		return productRepository.findByNameContainingIgnoreCase(name);
	}


	public Page<Product> findAllPageable(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public  Page<Product> findByNombreContainingIgnoreCasePageable(String name, Pageable pageable)
	{
		return productRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	

}
