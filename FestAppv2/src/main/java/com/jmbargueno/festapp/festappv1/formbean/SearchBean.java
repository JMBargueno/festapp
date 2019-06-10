package com.jmbargueno.festapp.festappv1.formbean;
/**
 * Clase del formbean
 * @author jmbargueno
 *
 */

public class SearchBean {

	/**
	 * Cadena de caracteres tipo String a la que se le pasa lo que queremos buscar
	 * en nuestro bucle para mostrarlo en nuestra plantilla
	 */
	private String search;

	public SearchBean() {

	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
