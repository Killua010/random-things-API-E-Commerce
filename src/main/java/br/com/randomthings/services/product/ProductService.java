package br.com.randomthings.services.product;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.randomthings.domain.Product;
import br.com.randomthings.services.IService;
import br.com.randomthings.viewhelper.ProductViewHelper;

public interface ProductService extends IService<Product> {
	Page<Product> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy);
	
	Product update(Product product);
}
