package br.com.randomthings.services.product.web;

import java.util.List;

import br.com.randomthings.viewhelper.ProductViewHelper;

public interface ProductServiceWeb {
	List<ProductViewHelper> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy);
	
	List<ProductViewHelper> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction, String orderBy, Long subCategoryId);
	
	List<ProductViewHelper> findBy(String param);
}
