/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	ProductRepository productoRepository;

	@Autowired
	StorageService storageService;

	public void add(Product p, MultipartFile file) {

		String fileName = storageService.store(file);// Guarda la imagen
		// Guardamos nombre de la imagen almacenada en el atributo de la entidad
		p.setImgUrl(fileName);
		// Guardamos la entidad en la base de datos y en ella ya irá el nombre del
		// archivo
		// en la correspondiente propiedad (fileUrl)
		productoRepository.save(p);

	}

	public List<Product> list() {

		List<Product> partialResult = productoRepository.findAll();
		List<Product> result = new LinkedList<Product>(partialResult);

		for (int i = 0; i < partialResult.size(); i++) {
			String fileName = partialResult.get(i).getImgUrl();
			result.get(i).setImgUrl(fileName);
		}

		return result;
	}

	public List<Product> productListFormBean() {

		List<Product> lista = productoRepository.findAll();

		lista = lista.stream().map(p -> {
			p.setImgUrl("/images/" + p.getImgUrl());
			return p;
		}).collect(Collectors.toList());

		return lista;
	}

}
