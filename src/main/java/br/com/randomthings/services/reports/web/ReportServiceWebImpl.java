package br.com.randomthings.services.reports.web;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Gender;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.dto.specific.ReportDTO;
import br.com.randomthings.services.category.CategoryService;
import br.com.randomthings.services.order.OrderService;
import br.com.randomthings.services.product.ProductService;

@Service
public class ReportServiceWebImpl implements ReportServiceWeb {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Override
	public Integer[] getOrdersByMounths() {
		Integer[] ordersByMounth = new Integer[12];
		
		List<Order> orders = orderService.findAll();
		
		for(int i = 0; i < 12; i++) {
			ordersByMounth[i] = 0;
			for(Order order: orders) {
				if(order.getCreationDate().getMonth().getValue() == i+1) {
					ordersByMounth[i] += 1;
				}
			}
		}
		
		return ordersByMounth;
	}

	@Override
	public List<ReportDTO> getOrdersByCategory() {
		Map<Category, Integer> mapCategories = new HashMap<>();
		
		for(Category category: categoryService.findAll()) {
			mapCategories.put(category, 0);
		}
		
		for(Order order: orderService.findAll()) {
			for(OrderItem item: order.getItems()) {
				for(SubCategory subCategory :item.getProduct().getSubCategory()) {
					mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
				}
			}
		}
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapCategories.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 4);
	}

	@Override
	public List<ReportDTO> getOrdersByProducts() {
		Map<Product, Integer> mapProduct = new HashMap<>();
		
		for(Product product: productService.findAll()) {
			mapProduct.put(product, 0);
		}
		
		for(Order order: orderService.findAll()) {
			for(OrderItem item: order.getItems()) {
				mapProduct.put(item.getProduct(), mapProduct.get(item.getProduct()) + 1);		
			}
		}
		
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapProduct.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 5);
	}

	@Override
	public List<List<ReportDTO>> getOrdersCategoryByMounths() {
		
		List<Category> categories = new ArrayList<Category>();
		Map<Category, Integer> mapCategories = new HashMap<>();
		List<Order> orders = orderService.findAll();
		for(Category category: categoryService.findAll()) {
			mapCategories.put(category, 0);
		}
		
		for(Order order: orders) {
			for(OrderItem item: order.getItems()) {
				for(SubCategory subCategory :item.getProduct().getSubCategory()) {
					mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
				}
			}
		}
		
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapCategories.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for (Map.Entry<Category, Integer> pair : mapCategories.entrySet()) {
				if(pair.getKey().getName().equals(dtos.get(i).getName())) {
					categories.add(pair.getKey());
				}   
			}
		}
		
		
		List<Map<Category, Integer>> list = new ArrayList<>();
		
		
		
		for(int i = 0; i < 12; i++) {
			Map<Category, Integer> map = new HashMap<>();
			for(Category category: categories) {
				map.put(category, 0);
			}
			boolean flagAux = false; 
			for(Order order: orders) {
				if(order.getCreationDate().getMonth().getValue() == i+1) {
					for(OrderItem item: order.getItems()) {
						for(SubCategory subCategory :item.getProduct().getSubCategory()) {
							if(categories.contains(subCategory.getCategory())) {
								int val = map.get(subCategory.getCategory()) + 1;
								map.put(subCategory.getCategory(), val);
								
								flagAux = true;
								break;
							}
						}
						if(flagAux == true) {
							break;
						}
					}
				}
			}
			list.add(map);
		}
		
		
		List<List<ReportDTO>> dtosResult = new ArrayList<List<ReportDTO>>();
		
		for(Map<Category, Integer> map: list) {
			List<ReportDTO> dtosList = new ArrayList<ReportDTO>();
			
			map.forEach((key, value)->{
				dtosList.add(new ReportDTO(key.getName(), value));
			});
			
			dtosResult.add(dtosList.subList(0, 3));
			
		}
		
		return dtosResult;
	}

	@Override
	public List<ReportDTO> getOrdersCategoryGender(String gender, int mounth) {
		Map<Category, Integer> mapCategories = new HashMap<>();
		
		for(Category category: categoryService.findAll()) {
			mapCategories.put(category, 0);
		}
		
		for(Order order: orderService.findAll()) {
			if(order.getClient().getGender().equals(Gender.valueOf(gender))) {
				if(mounth == 0) {
					for(OrderItem item: order.getItems()) {
						for(SubCategory subCategory :item.getProduct().getSubCategory()) {
							mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
						}
					}
				} else if(order.getCreationDate().getMonthValue() == mounth) {
					for(OrderItem item: order.getItems()) {
						for(SubCategory subCategory :item.getProduct().getSubCategory()) {
							mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
						}
					}
				}
			}
		}
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapCategories.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 4);
	}

	@Override
	public List<ReportDTO> getOrdersCategoryGenderAge(String gender, int start, int end, int mounth) {
		Map<Category, Integer> mapCategories = new HashMap<>();
		
		for(Category category: categoryService.findAll()) {
			mapCategories.put(category, 0);
		}
		
		for(Order order: orderService.findAll()) {
			int age = LocalDate.now().getYear() - order.getClient().getBirthDate().getYear(); 
			if(order.getClient().getGender().equals(Gender.valueOf(gender))
					&& (age >= start && age <= end)) {
				if(mounth == 0) {
					for(OrderItem item: order.getItems()) {
						for(SubCategory subCategory :item.getProduct().getSubCategory()) {
							mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
						}
					}
				} else if(order.getCreationDate().getMonthValue() == mounth) {
					for(OrderItem item: order.getItems()) {
						for(SubCategory subCategory :item.getProduct().getSubCategory()) {
							mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
						}
					}
				}
			}
		}
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapCategories.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 4);
	}

	@Override
	public List<List<ReportDTO>> getOrdersProductByMounths() {
		List<Product> producties = new ArrayList<Product>();
		Map<Product, Integer> mapProducties = new HashMap<>();
		List<Order> orders = orderService.findAll();
		for(Product product: productService.findAll()) {
			mapProducties.put(product, 0);
		}
		
		for(Order order: orders) {
			for(OrderItem item: order.getItems()) {
				int val = mapProducties.get(item.getProduct()) + 1;
				mapProducties.put(item.getProduct(), val);
			}
		}
		
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapProducties.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for (Map.Entry<Product, Integer> pair : mapProducties.entrySet()) {
				if(pair.getKey().getName().equals(dtos.get(i).getName())) {
					producties.add(pair.getKey());
				}   
			}
		}
		
		List<Map<Product, Integer>> list = new ArrayList<>();
		
		for(int i = 0; i < 12; i++) {
			Map<Product, Integer> map = new HashMap<>();
			for(Product product: producties) {
				map.put(product, 0);
			}
			boolean flagAux = false; 
			for(Order order: orders) {
				if(order.getCreationDate().getMonth().getValue() == i+1) {
					for(OrderItem item: order.getItems()) {
						if(producties.contains(item.getProduct())) {
							int val = map.get(item.getProduct()) + 1;
							map.put(item.getProduct(), val);
							
							flagAux = true;
							break;
						}
						if(flagAux == true) {
							break;
						}
					}
				}
			}
			list.add(map);
		}
		
		
		List<List<ReportDTO>> dtosResult = new ArrayList<List<ReportDTO>>();
		
		for(Map<Product, Integer> map: list) {
			List<ReportDTO> dtosList = new ArrayList<ReportDTO>();
			
			map.forEach((key, value)->{
				dtosList.add(new ReportDTO(key.getName(), value));
			});
			
			dtosResult.add(dtosList.subList(0, 3));
			
		}
		
		return dtosResult;
	}

	@Override
	public List<ReportDTO> getOrdersProductGender(String gender, int mounth) {
		Map<Product, Integer> mapProduct = new HashMap<>();
		
		for(Product product: productService.findAll()) {
			mapProduct.put(product, 0);
		}
		
		for(Order order: orderService.findAll()) {
			if(order.getClient().getGender().equals(Gender.valueOf(gender))) {
				if(mounth == 0) {
					for(OrderItem item: order.getItems()) {
						int val = mapProduct.get(item.getProduct()) + 1;
						mapProduct.put(item.getProduct(), val);
					}
				} else if(order.getCreationDate().getMonthValue() == mounth) {
					for(OrderItem item: order.getItems()) {
						int val = mapProduct.get(item.getProduct()) + 1;
						mapProduct.put(item.getProduct(), val);
					}
				}
			}
		}
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapProduct.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 5);
	}

	@Override
	public List<ReportDTO> getOrdersProductGenderAge(String gender, int start, int end, int mounth) {
		Map<Product, Integer> mapProduct = new HashMap<>();
		
		for(Product product: productService.findAll()) {
			mapProduct.put(product, 0);
		}
		
		for(Order order: orderService.findAll()) {
			int age = LocalDate.now().getYear() - order.getClient().getBirthDate().getYear(); 
			if(order.getClient().getGender().equals(Gender.valueOf(gender))
					&& (age >= start && age <= end)) {
				if(mounth == 0) {
					for(OrderItem item: order.getItems()) {
						int val = mapProduct.get(item.getProduct()) + 1;
						mapProduct.put(item.getProduct(), val);
					}
				} else if(order.getCreationDate().getMonthValue() == mounth) {
					for(OrderItem item: order.getItems()) {
						int val = mapProduct.get(item.getProduct()) + 1;
						mapProduct.put(item.getProduct(), val);
					}
				}
			}
		}
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		
		mapProduct.forEach((key, value)->{
			dtos.add(new ReportDTO(key.getName(), value));
		});
		
		boolean flag = false;
		ReportDTO aux;
		while(flag != true) {
			flag = true;
			for(int i = 0; i < dtos.size() - 1; i++) {
				if(((int)dtos.get(i).getValue()) < ((int)dtos.get(i + 1).getValue())){
					aux = dtos.get(i);
					dtos.set(i, dtos.get(i + 1));
					dtos.set(i + 1, aux);
					flag = false;
				}
			}
		}
		
		return dtos.subList(0, 5);
	}

}
