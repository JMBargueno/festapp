/**
 * 
 */
package com.jmbargueno.festapp.festappv1.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jmbargueno.festapp.festappv1.model.Product;
import com.jmbargueno.festapp.festappv1.repository.ProductRepository;
import com.jmbargueno.festapp.festappv1.storage.StorageService;

/**
 * @author jmbargueno
 *
 */
@Service
public class UploadService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StorageService storageService;
	
	public void add(Product t, MultipartFile file) {
		
		String fileName = storageService.store(file);//Guarda la imagen
		//Guardamos nombre de la imagen almacenada en el atributo de la entidad
		t.setImgUrl(fileName);
		//Guardamos la entidad en la base de datos y en ella ya irá el nombre del archivo
		//en la correspondiente propiedad (fileUrl)
		productRepository.save(t);
		
	}
	
	/** Método que devuelve una lista de entidades con sus ficheros
	 * a los que se les ha guardado dentro de su atributo URL
	 * el nombre de la URL images/nombredelarchivo.
	 * Este /images/ debe ser el mismo que el que hayamos dado en la clase 
	 * MvcConfig del paquete Hello*/
	
	public List<Product> list() {
				
		List<Product> partialResult = productRepository.findAll();
		List<Product> result = new LinkedList<Product>(partialResult);
		
		for(int i = 0; i < partialResult.size(); i++) {
			String fileName = partialResult.get(i).getImgUrl();
			result.get(i).setImgUrl("/images/"+ fileName);
		}
						
		return result;
	}
}
