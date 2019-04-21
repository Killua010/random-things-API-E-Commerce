package br.com.randomthings.services.product;

import org.springframework.data.domain.Page;

import br.com.randomthings.domain.Product;
import br.com.randomthings.services.IService;

public interface ProductService extends IService<Product> {
	Page<Product> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy);
	
	Product update(Product product);
}
