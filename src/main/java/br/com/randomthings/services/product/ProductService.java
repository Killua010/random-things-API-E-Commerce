package br.com.randomthings.services.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.services.IService;

public interface ProductService extends IService<Product, Long> {
	Page<Product> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy);
	
	Page<Product> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction, String orderBy, SubCategory subCategory);
	
	List<Product> findBy(@Param("param") String param);

	List<Product> findByCategory(Category category);
}
