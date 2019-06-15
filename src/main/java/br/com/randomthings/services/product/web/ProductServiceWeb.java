package br.com.randomthings.services.product.web;

import java.util.List;

import br.com.randomthings.dto.ProductDTO;

public interface ProductServiceWeb {
	List<ProductDTO> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy);
	
	List<ProductDTO> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction, String orderBy, Long subCategoryId);
	
	List<ProductDTO> findBy(String param);

	List<ProductDTO> findByCategoryId(Long categoryId);

	List<ProductDTO> getPopularProduct();
}
