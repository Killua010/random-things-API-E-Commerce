package br.com.randomthings.services.product.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.sub_category.SubCategoryService;
import br.com.randomthings.viewhelper.ProductViewHelper;

@Service
public class ProductServiceWebImpl implements ProductServiceWeb {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Override
	public List<ProductViewHelper> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy) {
		List<ProductViewHelper> helpers = new ArrayList<>();
		Page<Product> pages = productService.getPageabled(pageNumber, qtdPage, direction, orderBy);
		for(Product product : pages) {
			ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(product);
			helper.setTotalPage(pages.getTotalPages());
			helpers.add(helper);
		}
		return helpers;
	}

	@Override
	public List<ProductViewHelper> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction,
			String orderBy, Long subCategoryId) {
		
		SubCategory subCategory = subCategoryService.findById(subCategoryId);
		
		List<ProductViewHelper> helpers = new ArrayList<>();
		
		Page<Product> pages = productService.getPageabledByCategory(pageNumber, qtdPage, direction, orderBy, subCategory);
		
		for(Product product : pages) {
			ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(product);
			helper.setTotalPage(pages.getTotalPages());
			helpers.add(helper);
		}
		
		return helpers;
	}

	@Override
	public List<ProductViewHelper> findBy(String param) {
		List<ProductViewHelper> helpers = new ArrayList<>();

		List<Product> products =  productService.findBy(param);
		for(Product product : products) {
			ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(product);
			helpers.add(helper);
		}
		return helpers;
	}

	
}
