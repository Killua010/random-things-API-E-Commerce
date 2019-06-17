package br.com.randomthings.services.product.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.dto.CategoryDTO;
import br.com.randomthings.dto.ProductDTO;
import br.com.randomthings.dto.specific.ReportDTO;
import br.com.randomthings.services.category.CategoryService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.sub_category.SubCategoryService;

@Service
public class ProductServiceWebImpl implements ProductServiceWeb {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public List<ProductDTO> getPageabled(Integer pageNumber, Integer qtdPage, String direction, String orderBy) {
		List<ProductDTO> helpers = new ArrayList<>();
		Page<Product> pages = productService.getPageabled(pageNumber, qtdPage, direction, orderBy);
		for(Product product : pages) {
			ProductDTO helper = (ProductDTO) new ProductDTO().from(product);
			helper.setTotalPage(pages.getTotalPages());
			helpers.add(helper);
		}
		return helpers;
	}

	@Override
	public List<ProductDTO> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction,
			String orderBy, Long subCategoryId) {
		
		SubCategory subCategory = subCategoryService.findById(subCategoryId);
		
		List<ProductDTO> helpers = new ArrayList<>();
		
		Page<Product> pages = productService.getPageabledByCategory(pageNumber, qtdPage, direction, orderBy, subCategory);
		
		for(Product product : pages) {
			ProductDTO helper = (ProductDTO) new ProductDTO().from(product);
			helper.setTotalPage(pages.getTotalPages());
			helpers.add(helper);
		}
		
		return helpers;
	}

	@Override
	public List<ProductDTO> findBy(String param) {
		List<ProductDTO> helpers = new ArrayList<>();

		List<Product> products =  productService.findBy(param);
		for(Product product : products) {
			ProductDTO helper = (ProductDTO) new ProductDTO().from(product);
			helpers.add(helper);
		}
		return helpers;
	}

	@Override
	public List<ProductDTO> findByCategoryId(Long categoryId) {
		List<ProductDTO> helpers = new ArrayList<>();
		Category category = categoryService.findById(categoryId);
		
		List<Product> products =  productService.findByCategory(category);
		for(Product product : products) {
			ProductDTO helper = (ProductDTO) new ProductDTO().from(product);
			helpers.add(helper);
		}
		return helpers;
	}

	@Override
	public List<ProductDTO> getPopularProduct() {
		Map<Product, Integer> mapProduct = new HashMap<>();

		for (Product product : productService.findAllByStatusTrue()) {
			mapProduct.put(product, 0);
		}

		for (Order order : orderService.findAll()) {
			for (OrderItem item : order.getItems()) {
				if(item.getProduct().getStatus() == true) {
					mapProduct.put(item.getProduct(), mapProduct.get(item.getProduct()) + item.getQuantity());
				}
			}
		}
		
		List<ReportDTO> dtosList = new ArrayList<ReportDTO>();
		mapProduct.forEach((key, value) -> {
			dtosList.add(new ReportDTO(key.getId().toString(), value));
		});
		
		List<ReportDTO> dtos = dtosList;

		boolean flag = false;
		ReportDTO aux;
		while (flag != true) {
			flag = true;
			for (int i = 0; i < dtos.size() - 1; i++) {
				if (((int) dtos.get(i).getValue()) < ((int) dtos.get(i + 1).getValue())) {
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		dtos = dtos.subList(0, 4);
	
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for(ReportDTO dto: dtos) {
			ProductDTO productDTO = new ProductDTO();
			productDTOs.add((ProductDTO) productDTO.from(
					productService.findById(Long.parseLong(dto.getName()))));
		}
		
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getInactivesProduct() {
		List<ProductDTO> helpers = new ArrayList<>();
		List<Product> products =  productService.findAllByStatusFalse();
		for(Product product : products) {
			ProductDTO helper = (ProductDTO) new ProductDTO().from(product);
			helpers.add(helper);
		}
		return helpers;
	}

	
}
