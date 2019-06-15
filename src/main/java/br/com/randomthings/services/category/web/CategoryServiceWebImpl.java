package br.com.randomthings.services.category.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.NamedEntity;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.dto.CategoryDTO;
import br.com.randomthings.dto.specific.ReportDTO;
import br.com.randomthings.services.category.CategoryService;
import br.com.randomthings.services.order.OrderService;

@Service
public class CategoryServiceWebImpl implements CategoryServiceWeb{

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public List<CategoryDTO> getPopularCategory() {
		Map<Category, Integer> mapCategories = new HashMap<>();

		for (Category category : categoryService.findAll()) {
			mapCategories.put(category, 0);
		}

		for (Order order : orderService.findAll()) {
			for (OrderItem item : order.getItems()) {
				for (SubCategory subCategory : item.getProduct().getSubCategory()) {
					mapCategories.put(subCategory.getCategory(), mapCategories.get(subCategory.getCategory()) + 1);
				}
			}
		}
		
		List<ReportDTO> dtosList = new ArrayList<ReportDTO>();
		mapCategories.forEach((key, value) -> {
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
		
		dtos = dtos.subList(0, 2);
	
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		
		for(ReportDTO dto: dtos) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTOs.add((CategoryDTO) categoryDTO.from(
					categoryService.findById(Long.parseLong(dto.getName()))));
		}
		
		return categoryDTOs;
	}

}
